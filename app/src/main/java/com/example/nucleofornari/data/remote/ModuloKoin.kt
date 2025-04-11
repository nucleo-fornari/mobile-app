package com.example.nucleofornari.data.remote

import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.presentation.screen.auth.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val moduloGeral = module {

    // single -> devolve a MESMA instância para todos que pedirem
    single<SessaoUsuario> {
        SessaoUsuario(
            userId = null,
            nome = "",
            email = "",
            funcao = "",
            salaId = null,
            token = ""
        )
    }
}

val moduloApiReal = module {

    single {
        SessaoUsuario(
            userId = null,
            nome = "",
            email = "",
            funcao = "",
            salaId = null,
            token = ""
        )
    }

    single {
        val token = get<SessaoUsuario>().token
        RetrofitClient.getInstance(token)
    }

    factory { get<Retrofit>().create(UsuarioApiService::class.java) }
//    factory { get<Retrofit>().create(OutroApiService::class.java) }

    viewModel { LoginViewModel(get(), get()) }
//    viewModel { OutroViewModel(get()) }
}

//val moduloApiReal = module {
//
//    // factory -> devolve uma NOVA instância para cada que pedir
//    factory<UsuarioApiService> {
//        FeiraApi.getApi(get<SessaoUsuario>().token)
//        /*
//        O get() utiliza o próprio Koin para pegar a instância do objeto
//        que foi solicitado
//        No caso, vai retornar a instância única do SessaoUsuario
//        e usar seu token
//         */
//    }
//
//    viewModel<LoginViewModel> {
//        LoginViewModel(get<UsuarioApiService>())
//        /*
//        O get() utiliza o próprio Koin para pegar a instância do objeto
//        que foi solicitado
//        No caso, vai retornar uma instância do FeiraApiService
//         */
//    }
//}