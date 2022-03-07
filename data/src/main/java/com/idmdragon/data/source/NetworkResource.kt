package com.idmdragon.data.source

import com.idmdragon.data.source.remote.response.ApiResponse
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(convertResponseToModel(apiResponse.data)))
                onFetchSuccess()
            }
            is ApiResponse.Error -> {
                emit(
                    Resource.Error<ResultType>(
                        apiResponse.message
                    )
                )
            }
        }
    }

    protected abstract fun convertResponseToModel(response: RequestType): ResultType

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected open suspend fun onFetchSuccess() {}

    protected open suspend fun onFetchFailed() {}

    fun asFlow() = result

}