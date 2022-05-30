<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">VIT</a>
        <button aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
                class="navbar-toggler"
                data-bs-target="#navbarSupportedContent"
                data-bs-toggle="collapse"
                type="button">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/">На главную</a>
                </li>
                <#if user??>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/applications">Список приложений</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/applications/add">Добавить приложение</a>
                    </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/addEvent">Добавить событие</a>
                    </li>
                </#if>
            </ul>

            <#if known>
                <div class="navbar-text me-3">
                    ${name}
                </div>
                <@l.logout/>
            <#else>
                <a class="btn btn-primary me-3" href="/login" type="submit">Войти</a>
                <a class="btn btn-primary me-3" href="/registration" type="submit">Регистрация</a>
            </#if>
        </div>
    </div>
</nav>
