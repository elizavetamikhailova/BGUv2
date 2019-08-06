package project.elizavetamikhailova.bguv2.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import project.elizavetamikhailova.bguv2.di.factory.ViewModelBuilder
import project.elizavetamikhailova.bguv2.di.modules.AppModule
import project.elizavetamikhailova.bguv2.di.modules.MainModule
import project.elizavetamikhailova.bguv2.di.modules.DataBaseModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelBuilder::class,
        DataBaseModule::class,
        MainModule::class
    ])
interface AppComponent : AndroidInjector<BguApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BguApplication>()
}