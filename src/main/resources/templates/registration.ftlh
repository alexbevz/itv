<#import "parts/common.ftlh" as c>
<#import "parts/security.ftlh" as s>

<@c.page "Страница регистрации">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Регистрация</h1>
            <@s.registration/>
            <div class="form-group">
                <a href="/login">Авторизация</a>
            </div>
        </div>
    </div>
</@c.page>
