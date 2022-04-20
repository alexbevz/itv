<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page "Страница регистрации">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Регистрация</h1>
            <@l.login "/registration" true />
        </div>
    </div>
</@c.page>
