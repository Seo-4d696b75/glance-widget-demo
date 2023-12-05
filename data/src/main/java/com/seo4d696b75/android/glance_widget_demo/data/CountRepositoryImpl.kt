package com.seo4d696b75.android.glance_widget_demo.data

import com.seo4d696b75.android.glance_widget_demo.domain.CountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import kotlin.math.min

class CountRepositoryImpl @Inject constructor() : CountRepository {
    override fun increment(current: Int): Int {
        return min(current + 1, 99)
    }
}

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
interface CountRepositoryModule {
    @Binds
    fun bindCountyRepository(impl: CountRepositoryImpl): CountRepository
}
