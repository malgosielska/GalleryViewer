import com.example.galleryviewer.api_connection.ApiConstants
import com.example.galleryviewer.api_connection.PhotosSearchResponse
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

// Retrofit's base urls must end with a "/" or OkHttp will through an exception!
private const val BASE_URL = "https://api.flickr.com/services/rest/"
private const val CONNECTION_TIMEOUT_MS: Long = 10

object WebClient {
    val client: ApiService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                )
            )
            .client(
                OkHttpClient.Builder().connectTimeout(
                    CONNECTION_TIMEOUT_MS,
                    TimeUnit.SECONDS
                ).addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .build()
            .create(ApiService::class.java)
    }
}

interface ApiService {
    // Either add the api key to a file that is not being tracked with your version control system,
    // or add a gradle script to add it as a string resource (per Google's recommendation)
    @GET("?method=flickr.interestingness.getList&api_key=${ApiConstants.FLICKR_API_KEY}&per_page=500&format=json&nojsoncallback=1&extras=date_taken,owner_name,tags,views")
    suspend fun fetchImages(): PhotosSearchResponse
}