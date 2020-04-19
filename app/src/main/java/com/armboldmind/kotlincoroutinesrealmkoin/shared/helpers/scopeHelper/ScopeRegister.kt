package com.armboldmind.kotlincoroutinesrealmkoin.shared.helpers.scopeHelper

import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.AuthorizationScope
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.MainScope
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.PagingScope
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.TestScope


@MainScope
@AuthorizationScope
@PagingScope
@TestScope
class ScopeRegister