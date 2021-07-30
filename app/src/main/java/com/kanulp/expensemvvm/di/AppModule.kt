package com.kanulp.expensemvvm.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kanulp.expensemvvm.data.remote.ExpenseRemoteDataSource
import com.kanulp.expensemvvm.data.remote.ExpensesApiService
import com.kanulp.expensemvvm.repository.ExpenseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideExpanseApiService(retrofit: Retrofit): ExpensesApiService = retrofit.create(ExpensesApiService::class.java)

    @Singleton
    @Provides
    fun provideExpanseRemoteDataSource(apiService: ExpensesApiService) = ExpenseRemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: ExpenseRemoteDataSource) =
            ExpenseRepository(remoteDataSource)

}

/*
[{"amount":20,"attachments":[{"_id":"abc","userId":"123","image":"https://forever.travel-assets.com/flex/flexmanager/images/2021/04/02/ORB_TravelAsYouAre_imgB_1440x808_20210402.jpg?impolicy=fcrop&w=400&h=225&q=mediumLow"}],"date":"2021-07-15T00:00:00.000Z","expenseTitle":"Lunch","category":"Other","currencyCode":"USD","user":{"email":"test@gmail.com","created":"2021-07-09T17:23:27.465Z","id":"123"}},{"amount":222,"attachments":[{"_id":"abc2","userId":"1234","image":"https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F28%2F2021%2F03%2F30%2FLGBTQ-couple-riding-bikes-orbitz-campaign-ORBITZ0321.jpg"}],"date":"2021-07-16T00:00:00.000Z","expenseTitle":"Hotel","category":"Accommodation","currencyCode":"CAD","user":{"email":"test2@gmail.com","created":"2021-07-10T17:23:27.465Z","id":"1234"}},{"amount":52,"attachments":[{"_id":"abc3","userId":"1234","image":"https://s3-us-east-2.amazonaws.com/orbitz-media/blog/wp-content/uploads/2016/08/16124120/Orbitz-Couples-Travel-Tips-Getting-Stamped-002.jpg"}],"date":"2021-07-17T00:00:00.000Z","expenseTitle":"CLUB","category":"Party","currencyCode":"EUR","user":{"email":"test3@gmail.com","created":"2021-07-11T17:23:27.465Z","id":"1234"}}]
*/