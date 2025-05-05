package com.example.nucleofornari.data.remote

import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.remote.service.EventoApi
import com.example.nucleofornari.data.remote.service.EventoApiService
import com.example.nucleofornari.data.remote.service.UsuarioApi
import com.example.nucleofornari.data.remote.service.UsuarioApiService
import com.example.nucleofornari.presentation.common.component.calendar.CalendarViewModel
import com.example.nucleofornari.presentation.screen.auth.login.LoginViewModel
import com.example.nucleofornari.presentation.screen.auth.login.RecuperacaoSenhaViewModel
import com.example.nucleofornari.presentation.screen.responsavel.AgendaViewModel
import com.example.nucleofornari.presentation.screen.professor.CategoriasViewModel
import com.example.nucleofornari.presentation.screen.professor.ChamadosViewModel
import com.example.nucleofornari.presentation.screen.professor.InicioProfessorViewModel
import com.example.nucleofornari.presentation.screen.responsavel.PublicacoesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduloGeral = module {
    single<SessaoUsuario> {
        SessaoUsuario()
    }
}

val moduloApiReal = module {
    factory<UsuarioApiService> {
        UsuarioApi.getApi(get<SessaoUsuario>().token)
    }

    factory<EventoApiService> {
        EventoApi.getApi(get<SessaoUsuario>().token)
    }

    viewModel<LoginViewModel> { LoginViewModel(get(),get()) }
    viewModel<AgendaViewModel> { AgendaViewModel(get(), get()) }
    viewModel<PublicacoesViewModel> { PublicacoesViewModel(get(), get()) }
    viewModel<CategoriasViewModel> { CategoriasViewModel(get()) }
    viewModel<ChamadosViewModel> { ChamadosViewModel(get(), get()) }
    viewModel<RecuperacaoSenhaViewModel>{ RecuperacaoSenhaViewModel(get()) }
    viewModel<CalendarViewModel>{ CalendarViewModel(get(), get())}
    viewModel<InicioProfessorViewModel>{ InicioProfessorViewModel(get(), get())}
}