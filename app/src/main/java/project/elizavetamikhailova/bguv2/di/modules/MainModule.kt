package project.elizavetamikhailova.bguv2.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import project.elizavetamikhailova.bguv2.di.factory.ViewModelKey
import project.elizavetamikhailova.bguv2.view.ui.activity.MainActivity
import project.elizavetamikhailova.bguv2.view.ui.fragments.statistic.StatisticFragment
import project.elizavetamikhailova.bguv2.view.ui.fragments.add_food.CategoryFragment
import project.elizavetamikhailova.bguv2.view.ui.fragments.add_food.ProductFragment
import project.elizavetamikhailova.bguv2.view.ui.fragments.add_food.UserProductFragment
import project.elizavetamikhailova.bguv2.view.ui.fragments.add_food.WeightFragment
import project.elizavetamikhailova.bguv2.view.ui.fragments.statistic.WeekStatisticFragment
import project.elizavetamikhailova.bguv2.view.ui.view_models.add_food.CategoryViewModel
import project.elizavetamikhailova.bguv2.view.ui.view_models.add_food.ProductViewModel
import project.elizavetamikhailova.bguv2.view.ui.view_models.add_food.UserProductsViewModel
import project.elizavetamikhailova.bguv2.view.ui.view_models.add_food.WeightViewModel
import project.elizavetamikhailova.bguv2.view.ui.view_models.statistic.StatisticViewModel
import project.elizavetamikhailova.bguv2.view.ui.view_models.statistic.WeekStatisticViewModel

@Module
internal abstract class MainModule {

        @ContributesAndroidInjector
        internal abstract fun categoryFragment(): CategoryFragment

        @ContributesAndroidInjector
        internal abstract fun mainActivity(): MainActivity

        @Binds
        @IntoMap
        @ViewModelKey(CategoryViewModel::class)
        abstract fun bindCategoryFragmentViewModel(viewModel: CategoryViewModel): ViewModel


        @ContributesAndroidInjector
        internal abstract fun productFragment() : ProductFragment

        @Binds
        @IntoMap
        @ViewModelKey(ProductViewModel::class)
        abstract fun bindProductFragmentViewModel(viewModel: ProductViewModel): ViewModel

        @ContributesAndroidInjector
        internal abstract fun weightFragment() : WeightFragment

        @Binds
        @IntoMap
        @ViewModelKey(WeightViewModel::class)
        abstract fun bindWeightFragmentViewModel(viewModel: WeightViewModel): ViewModel

        @ContributesAndroidInjector
        internal abstract fun userProductFragment() : UserProductFragment

        @Binds
        @IntoMap
        @ViewModelKey(UserProductsViewModel::class)
        abstract fun bindUserProductFragmentViewModel(viewModel: UserProductsViewModel): ViewModel

        @ContributesAndroidInjector
        internal abstract fun statisticFragment() : StatisticFragment

        @Binds
        @IntoMap
        @ViewModelKey(StatisticViewModel::class)
        abstract fun bindStatisticFragmentViewModel(viewModel: StatisticViewModel): ViewModel

        @ContributesAndroidInjector
        internal abstract fun weekStatisticFragment() : WeekStatisticFragment

        @Binds
        @IntoMap
        @ViewModelKey(WeekStatisticViewModel::class)
        abstract fun bindWeekStatisticFragmentViewModel(viewModel: WeekStatisticViewModel): ViewModel

}