// Replace ALL when(...).thenReturn stubs with doReturn:
doReturn(List.of(sampleUser))
    .when(ldapTemplate)
    .search(ArgumentMatchers.any(), ArgumentMatchers.any());

    // ❌ Type parameter causes lambda mismatch
ArgumentMatchers.<AttributesMapper>any()

// ✅ No type parameter — matches lambda
ArgumentMatchers.any()
