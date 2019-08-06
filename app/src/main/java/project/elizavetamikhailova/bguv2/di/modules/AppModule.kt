package project.elizavetamikhailova.bguv2.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import project.elizavetamikhailova.bguv2.di.BguApplication


@Module
class AppModule{

    @Provides
    fun providesContext(application: BguApplication): Context {
        return application.applicationContext
    }
}