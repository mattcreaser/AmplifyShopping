object Deps {
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"

    const val compose_ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val compose_material = "androidx.compose.material:material:${Versions.compose}"
    const val compose_tooling = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val compose_activity = "androidx.activity:activity-compose:${Versions.compose_activity}"
    const val compose_viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.compose_viewmodel}"

    const val amplify_core = "com.amplifyframework:core-kotlin:${Versions.amplify_ktx}"
    const val amplify_api = "com.amplifyframework:aws-api:${Versions.amplify}"
    const val amplify_datastore = "com.amplifyframework:aws-datastore:${Versions.amplify}"

    const val desugar = "com.android.tools:desugar_jdk_libs:${Versions.desugar}"
}