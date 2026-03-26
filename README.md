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
import java.util.Collections;
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
        dellS3Service = new DellS3Service(s3Client);
        ReflectionTestUtils.setField(dellS3Service, "bucketName", "test-bucket");
    }

    // =========================================================================
    // uploadMultipartFile()
    // =========================================================================

    @Test
    @DisplayName("uploadMultipartFile - calls putObject with correct bucket and key")
    void uploadMultipartFile_callsPutObjectWithCorrectBucketAndKey() throws IOException {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file", "test.pdf", "application/pdf", "PDF content".getBytes()
        );

        dellS3Service.uploadMultipartFile("uploads/case/100/1", mockFile);

        ArgumentCaptor<PutObjectRequest> captor = ArgumentCaptor.forClass(PutObjectRequest.class);
        verify(s3Client, times(1)).putObject(captor.capture(), any(RequestBody.class));

        assertThat(captor.getValue().bucket()).isEqualTo("test-bucket");
        assertThat(captor.getValue().key()).isEqualTo("uploads/case/100/1");
    }

    @Test
    @DisplayName("uploadMultipartFile - sets correct content type")
    void uploadMultipartFile_setsCorrectContentType() throws IOException {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file", "image.png", "image/png", "image data".getBytes()
        );

        dellS3Service.uploadMultipartFile("uploads/case/100/2", mockFile);

        ArgumentCaptor<PutObjectRequest> captor = ArgumentCaptor.forClass(PutObjectRequest.class);
        verify(s3Client).putObject(captor.capture(), any(RequestBody.class));

        assertThat(captor.getValue().contentType()).isEqualTo("image/png");
    }

    @Test
    @DisplayName("uploadMultipartFile - calls putObject exactly once")
    void uploadMultipartFile_callsPutObjectExactlyOnce() throws IOException {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file", "doc.txt", "text/plain", "text".getBytes()
        );

        dellS3Service.uploadMultipartFile("uploads/test/key", mockFile);

        verify(s3Client, times(1)).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }

    @Test
    @DisplayName("uploadMultipartFile - throws IOException when stream fails")
    void uploadMultipartFile_streamFails_throwsIOException() throws IOException {
        MockMultipartFile mockFile = mock(MockMultipartFile.class);
        when(mockFile.getInputStream()).thenThrow(new IOException("Stream error"));
        when(mockFile.getContentType()).thenReturn("application/pdf");
        when(mockFile.getSize()).thenReturn(100L);

        assertThatThrownBy(() -> dellS3Service.uploadMultipartFile("key", mockFile))
                .isInstanceOf(IOException.class)
                .hasMessageContaining("Stream error");
    }

    // =========================================================================
    // downloadFile()
    // =========================================================================

    @SuppressWarnings("unchecked")
    private ResponseBytes<GetObjectResponse> mockResponseBytes(byte[] content) {
        ResponseBytes<GetObjectResponse> mockBytes = mock(ResponseBytes.class);
        when(mockBytes.asByteArray()).thenReturn(content);
        when(s3Client.getObject(any(GetObjectRequest.class), any(ResponseTransformer.class)))
                .thenReturn(mockBytes);
        return mockBytes;
    }

    @Test
    @DisplayName("downloadFile - returns readable resource")
    void downloadFile_returnsReadableResource() {
        mockResponseBytes("PDF content".getBytes());

        Resource result = dellS3Service.downloadFile("uploads/case/100/1");

        assertThat(result).isNotNull();
        assertThat(result.isReadable()).isTrue();
    }

    @Test
    @DisplayName("downloadFile - calls getObject with correct bucket and key")
    void downloadFile_callsGetObjectWithCorrectBucketAndKey() {
        mockResponseBytes("content".getBytes());

        dellS3Service.downloadFile("uploads/case/100/1");

        ArgumentCaptor<GetObjectRequest> captor = ArgumentCaptor.forClass(GetObjectRequest.class);
        verify(s3Client).getObject(captor.capture(), any(ResponseTransformer.class));

        assertThat(captor.getValue().bucket()).isEqualTo("test-bucket");
        assertThat(captor.getValue().key()).isEqualTo("uploads/case/100/1");
    }

    @Test
    @DisplayName("downloadFile - returns resource with correct byte content")
    void downloadFile_returnsCorrectByteContent() throws IOException {
        byte[] expected = "expected content".getBytes();
        mockResponseBytes(expected);

        Resource result = dellS3Service.downloadFile("uploads/case/100/1");

        assertThat(result.getContentAsByteArray()).isEqualTo(expected);
    }

    @Test
    @DisplayName("downloadFile - handles empty file content")
    void downloadFile_emptyContent_returnsEmptyResource() throws IOException {
        mockResponseBytes(new byte[0]);

        Resource result = dellS3Service.downloadFile("empty/file");

        assertThat(result.getContentAsByteArray()).isEmpty();
    }

    @Test
    @DisplayName("downloadFile - calls getObject exactly once")
    void downloadFile_callsGetObjectExactlyOnce() {
        mockResponseBytes(new byte[0]);

        dellS3Service.downloadFile("some/key");

        verify(s3Client, times(1))
                .getObject(any(GetObjectRequest.class), any(ResponseTransformer.class));
    }

    // =========================================================================
    // listFiles()
    // FIX: Use a real ListObjectsV2Response instead of mocking
    // ListObjectsV2Iterable.contents() which is final and can't be mocked
    // =========================================================================

    @Test
    @DisplayName("listFiles - returns list of file keys from S3")
    void listFiles_returnsFileKeys() {
        S3Object obj1 = S3Object.builder().key("uploads/case/100/1").build();
        S3Object obj2 = S3Object.builder().key("uploads/case/100/2").build();

        ListObjectsV2Iterable mockIterable = mock(ListObjectsV2Iterable.class,
                withSettings().defaultAnswer(invocation -> {
                    // Return a real stream for stream() calls
                    if (invocation.getMethod().getName().equals("stream")) {
                        return java.util.stream.Stream.of(
                                ListObjectsV2Response.builder()
                                        .contents(obj1, obj2)
                                        .build()
                        );
                    }
                    // For contents() — return iterable backed by real list
                    if (invocation.getMethod().getName().equals("contents")) {
                        return (Iterable<S3Object>) () -> List.of(obj1, obj2).iterator();
                    }
                    return invocation.callRealMethod();
                }));

        when(s3Client.listObjectsV2Paginator(any(ListObjectsV2Request.class)))
                .thenReturn(mockIterable);

        List<String> result = dellS3Service.listFiles();

        assertThat(result).containsExactly("uploads/case/100/1", "uploads/case/100/2");
    }

    @Test
    @DisplayName("listFiles - returns empty list when bucket is empty")
    void listFiles_emptyBucket_returnsEmptyList() {
        ListObjectsV2Iterable mockIterable = mock(ListObjectsV2Iterable.class,
                withSettings().defaultAnswer(invocation -> {
                    if (invocation.getMethod().getName().equals("contents")) {
                        return (Iterable<S3Object>) Collections::emptyIterator;
                    }
                    return invocation.callRealMethod();
                }));

        when(s3Client.listObjectsV2Paginator(any(ListObjectsV2Request.class)))
                .thenReturn(mockIterable);

        List<String> result = dellS3Service.listFiles();

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("listFiles - calls listObjectsV2Paginator with correct bucket")
    void listFiles_callsWithCorrectBucket() {
        ListObjectsV2Iterable mockIterable = mock(ListObjectsV2Iterable.class,
                withSettings().defaultAnswer(invocation -> {
                    if (invocation.getMethod().getName().equals("contents")) {
                        return (Iterable<S3Object>) Collections::emptyIterator;
                    }
                    return invocation.callRealMethod();
                }));

        when(s3Client.listObjectsV2Paginator(any(ListObjectsV2Request.class)))
                .thenReturn(mockIterable);

        dellS3Service.listFiles();

        ArgumentCaptor<ListObjectsV2Request> captor = ArgumentCaptor.forClass(ListObjectsV2Request.class);
        verify(s3Client).listObjectsV2Paginator(captor.capture());
        assertThat(captor.getValue().bucket()).isEqualTo("test-bucket");
    }

    @Test
    @DisplayName("listFiles - returns only key strings not full S3Object")
    void listFiles_returnsOnlyKeys() {
        S3Object obj = S3Object.builder().key("uploads/case/100/1").size(1024L).build();

        ListObjectsV2Iterable mockIterable = mock(ListObjectsV2Iterable.class,
                withSettings().defaultAnswer(invocation -> {
                    if (invocation.getMethod().getName().equals("contents")) {
                        return (Iterable<S3Object>) () -> List.of(obj).iterator();
                    }
                    return invocation.callRealMethod();
                }));

        when(s3Client.listObjectsV2Paginator(any(ListObjectsV2Request.class)))
                .thenReturn(mockIterable);

        List<String> result = dellS3Service.listFiles();

        assertThat(result).containsExactly("uploads/case/100/1");
        assertThat(result.get(0)).isInstanceOf(String.class);
    }
}
