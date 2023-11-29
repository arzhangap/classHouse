package com.arzhang.project.classhouse.Di

import com.arzhang.project.classhouse.Repository.CourseRepository
import com.arzhang.project.classhouse.Repository.OfflineCourseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCourseRepo(
        houseRepoImpl: OfflineCourseRepository
    ): CourseRepository

}