package project.elizavetamikhailova.bguv2.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BguApplication : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

}