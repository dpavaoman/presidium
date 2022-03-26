import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain

const val API_TOKEN = "8330701435dd80763e5bfe86be4f15f7"
const val API_URL = "https://api.smash.gg/gql/alpha"

class AuthorizationInterceptor(val token: String) : HttpInterceptor {
    override suspend fun intercept(request: HttpRequest, chain: HttpInterceptorChain): HttpResponse {
        return chain.proceed(request.newBuilder().addHeader("Authorization", "Bearer $API_TOKEN").build())
    }
}

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://api.smash.gg/gql/alpha")
    .addHttpInterceptor(AuthorizationInterceptor(API_TOKEN))
    .build()



