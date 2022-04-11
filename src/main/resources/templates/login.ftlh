<#import "parts/common.ftlh" as c>
<#import "parts/security.ftlh" as s>

<@c.page "Страница авторизации">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Страница авторизации</h1>

            <@s.login/>

            <div class="form-group">
                <a href="/registration">Регистрация</a>
            </div>
        </div>
    </div>
</@c.page>