<#import "parts/common.ftlh" as c>

<@c.page "Непредвиденная ошибка">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Ошибка</h1>
            <p>
                <#if infoError??>
                    ${infoError}
                <#else>
                    Ошибок не выявлено, вы сами перешли на страницу!
                    Переадресация на главную страницу будет произведена через 5 секунд!
                    <meta http-equiv="refresh" content="5;/"/>
                </#if>
            </p>
        </div>
    </div>
</@c.page>