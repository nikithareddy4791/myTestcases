package org.nnnn.ddd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("DellS3Service Tests")
class DellS3ServiceTest {

    @Mock
    private S3Client s3Client;

    private DellS3Service dellS3Service;

    @BeforeEach
    void setUp() {
        // Use constructor injection since DellS3Service takes S3Client via constructor
        dellS3Service = new DellS3Service(s3Client);
        // Inject bucket name via ReflectionTestUtils
        ReflectionTestUtils.setField(dellS3Service, "bucketName", "test-bucket");
    }

    // =========================================================================
    // uploadMultipartFile()
    // =========================================================================

    @Test
    @DisplayName("uploadMultipartFile - calls s3Client.putObject with correct bucket and key")
    void uploadMultipartFile_callsPutObjectWithCorrectBucketAndKey() throws IOException {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file", "test.pdf", "application/pdf", "PDF content".getBytes()
        );

        dellS3Service.uploadMultipartFile("uploads/case/100/1", mockFile);

        ArgumentCaptor<PutObjectRequest> requestCaptor = ArgumentCaptor.forClass(PutObjectRequest.class);
        verify(s3Client, times(1)).putObject(requestCaptor.capture(), any(RequestBody.class));

        PutObjectRequest captured = requestCaptor.getValue();
        assertThat(captured.bucket()).isEqualTo("test-bucket");
        assertThat(captured.key()).isEqualTo("uploads/case/100/1");
    }

    @Test
    @DisplayName("uploadMultipartFile - sets correct content type from multipart file")
    void uploadMultipartFile_setsCorrectContentType() throws IOException {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file", "image.png", "image/png", "image data".getBytes()
        );

        dellS3Service.uploadMultipartFile("uploads/case/100/2", mockFile);

        ArgumentCaptor<PutObjectRequest> requestCaptor = ArgumentCaptor.forClass(PutObjectRequest.class);
        verify(s3Client).putObject(requestCaptor.capture(), any(RequestBody.class));

        assertThat(requestCaptor.getValue().contentType()).isEqualTo("image/png");
    }

    @Test
    @DisplayName("uploadMultipartFile - calls s3Client.putObject exactly once")
    void uploadMultipartFile_callsPutObjectExactlyOnce() throws IOException {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file", "doc.txt", "text/plain", "text content".getBytes()
        );

        dellS3Service.uploadMultipartFile("uploads/test/key", mockFile);

        verify(s3Client, times(1)).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }

    @Test
    @DisplayName("uploadMultipartFile - throws IOException when file stream fails")
    void uploadMultipartFile_streamFails_throwsIOException() throws IOException {
        MockMultipartFile mockFile = mock(MockMultipartFile.class);
        when(mockFile.getInputStream()).thenThrow(new IOException("Stream error"));
        when(mockFile.getContentType()).thenReturn("application/pdf");
        when(mockFile.getSize()).thenReturn(100L);

        assertThatThrownBy(() -> dellS3Service.uploadMultipartFile("key", mockFile))
                .isInstanceOf(IOException.class)
                .hasMessageContaining("Stream error");
    }

    @Test
    @DisplayName("uploadMultipartFile - works with different file types")
    void uploadMultipartFile_differentFileTypes_uploadsSuccessfully() throws IOException {
        String[] contentTypes = {"application/pdf", "image/jpeg", "image/png", "text/plain"};

        for (String contentType : contentTypes) {
            MockMultipartFile file = new MockMultipartFile(
                    "file", "test", contentType, "content".getBytes()
            );
            dellS3Service.uploadMultipartFile("key/" + contentType, file);
        }

        verify(s3Client, times(4)).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }

    // =========================================================================
    // downloadFile()
    // =========================================================================

    @Test
    @DisplayName("downloadFile - returns ByteArrayResource with file content")
    void downloadFile_returnsResourceWithContent() {
        byte[] fileContent = "PDF content bytes".getBytes();

        @SuppressWarnings("unchecked")
        ResponseBytes<GetObjectResponse> mockResponseBytes = mock(ResponseBytes.class);
        when(mockResponseBytes.asByteArray()).thenReturn(fileContent);
        when(s3Client.getObject(any(GetObjectRequest.class), any(ResponseTransformer.class)))
                .thenReturn(mockResponseBytes);

        Resource result = dellS3Service.downloadFile("uploads/case/100/1");

        assertThat(result).isNotNull();
        assertThat(result.isReadable()).isTrue();
    }

    @Test
    @DisplayName("downloadFile - calls s3Client.getObject with correct bucket and key")
    void downloadFile_callsGetObjectWithCorrectBucketAndKey() {
        byte[] content = "content".getBytes();

        @SuppressWarnings("unchecked")
        ResponseBytes<GetObjectResponse> mockResponseBytes = mock(ResponseBytes.class);
        when(mockResponseBytes.asByteArray()).thenReturn(content);
        when(s3Client.getObject(any(GetObjectRequest.class), any(ResponseTransformer.class)))
                .thenReturn(mockResponseBytes);

        dellS3Service.downloadFile("uploads/case/100/1");

        ArgumentCaptor<GetObjectRequest> requestCaptor = ArgumentCaptor.forClass(GetObjectRequest.class);
        verify(s3Client).getObject(requestCaptor.capture(), any(ResponseTransformer.class));

        assertThat(requestCaptor.getValue().bucket()).isEqualTo("test-bucket");
        assertThat(requestCaptor.getValue().key()).isEqualTo("uploads/case/100/1");
    }

    @Test
    @DisplayName("downloadFile - returns resource with correct byte content")
    void downloadFile_returnsCorrectByteContent() throws IOException {
        byte[] expectedContent = "expected file content".getBytes();

        @SuppressWarnings("unchecked")
        ResponseBytes<GetObjectResponse> mockResponseBytes = mock(ResponseBytes.class);
        when(mockResponseBytes.asByteArray()).thenReturn(expectedContent);
        when(s3Client.getObject(any(GetObjectRequest.class), any(ResponseTransformer.class)))
                .thenReturn(mockResponseBytes);

        Resource result = dellS3Service.downloadFile("uploads/case/100/1");

        assertThat(result.getContentAsByteArray()).isEqualTo(expectedContent);
    }

    @Test
    @DisplayName("downloadFile - calls s3Client.getObject exactly once")
    void downloadFile_callsGetObjectExactlyOnce() {
        @SuppressWarnings("unchecked")
        ResponseBytes<GetObjectResponse> mockResponseBytes = mock(ResponseBytes.class);
        when(mockResponseBytes.asByteArray()).thenReturn(new byte[0]);
        when(s3Client.getObject(any(GetObjectRequest.class), any(ResponseTransformer.class)))
                .thenReturn(mockResponseBytes);

        dellS3Service.downloadFile("some/key");

        verify(s3Client, times(1)).getObject(any(GetObjectRequest.class), any(ResponseTransformer.class));
    }

    @Test
    @DisplayName("downloadFile - handles empty file content")
    void downloadFile_emptyContent_returnsEmptyResource() throws IOException {
        @SuppressWarnings("unchecked")
        ResponseBytes<GetObjectResponse> mockResponseBytes = mock(ResponseBytes.class);
        when(mockResponseBytes.asByteArray()).thenReturn(new byte[0]);
        when(s3Client.getObject(any(GetObjectRequest.class), any(ResponseTransformer.class)))
                .thenReturn(mockResponseBytes);

        Resource result = dellS3Service.downloadFile("empty/file");

        assertThat(result.getContentAsByteArray()).isEmpty();
    }

    // =========================================================================
    // listFiles()
    // =========================================================================

    @Test
    @DisplayName("listFiles - returns list of file keys from S3")
    void listFiles_returnsFileKeys() {
        S3Object s3Object1 = S3Object.builder().key("uploads/case/100/1").build();
        S3Object s3Object2 = S3Object.builder().key("uploads/case/100/2").build();

        ListObjectsV2Iterable mockIterable = mock(ListObjectsV2Iterable.class);
        when(mockIterable.contents()).thenReturn(
                software.amazon.awssdk.core.pagination.sync.SdkIterable.of(s3Object1, s3Object2)
        );
        when(s3Client.listObjectsV2Paginator(any(ListObjectsV2Request.class)))
                .thenReturn(mockIterable);

        List<String> result = dellS3Service.listFiles();

        assertThat(result).hasSize(2);
        assertThat(result).containsExactly("uploads/case/100/1", "uploads/case/100/2");
    }

    @Test
    @DisplayName("listFiles - returns empty list when bucket is empty")
    void listFiles_emptyBucket_returnsEmptyList() {
        ListObjectsV2Iterable mockIterable = mock(ListObjectsV2Iterable.class);
        when(mockIterable.contents()).thenReturn(
                software.amazon.awssdk.core.pagination.sync.SdkIterable.of()
        );
        when(s3Client.listObjectsV2Paginator(any(ListObjectsV2Request.class)))
                .thenReturn(mockIterable);

        List<String> result = dellS3Service.listFiles();

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("listFiles - calls s3Client.listObjectsV2Paginator with correct bucket")
    void listFiles_callsWithCorrectBucket() {
        ListObjectsV2Iterable mockIterable = mock(ListObjectsV2Iterable.class);
        when(mockIterable.contents()).thenReturn(
                software.amazon.awssdk.core.pagination.sync.SdkIterable.of()
        );
        when(s3Client.listObjectsV2Paginator(any(ListObjectsV2Request.class)))
                .thenReturn(mockIterable);

        dellS3Service.listFiles();

        ArgumentCaptor<ListObjectsV2Request> captor = ArgumentCaptor.forClass(ListObjectsV2Request.class);
        verify(s3Client).listObjectsV2Paginator(captor.capture());
        assertThat(captor.getValue().bucket()).isEqualTo("test-bucket");
    }
}
