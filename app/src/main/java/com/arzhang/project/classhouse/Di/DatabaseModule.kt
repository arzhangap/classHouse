package com.arzhang.project.classhouse.Di

import android.content.Context
import com.arzhang.project.classhouse.database.ClassHouseDao
import com.arzhang.project.classhouse.database.ClassHouseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): ClassHouseDatabase {
        return ClassHouseDatabase.getDatabase(context)
    }

    @Provides
    fun provideClassHouseDao(appDatabase: ClassHouseDatabase): ClassHouseDao {
        return appDatabase.classHouseDao()
    }

}