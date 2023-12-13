package com.seo4d696b75.android.glance_widget_demo.data

import com.seo4d696b75.android.glance_widget_demo.domain.CountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

class CountRepositoryImpl @Inject constructor() : CountRepository {
    override suspend fun increment(current: Int): Int {
        delay(1000L)
        return min(current + 1, 99)
    }

    override suspend fun decrement(current: Int): Int {
        delay(1000L)
        return max(current - 1, 0)
    }

    override fun validate(count: Int) = count in 0..99
}

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
interface CountRepositoryModule {
    @Binds
    fun bindCountyRepository(impl: CountRepositoryImpl): CountRepository
}
