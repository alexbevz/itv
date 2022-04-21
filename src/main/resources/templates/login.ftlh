<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page "Страница авторизации">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Авторизация</h1>
            <#if message??>
                <div class="alert alert-${messageType}" role="alert">
                    ${message}
                </div>
            </#if>
            <@l.login "/login" false />
        </div>
    </div>
</@c.page>