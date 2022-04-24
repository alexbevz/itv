<#import "parts/common.ftlh" as c>

<@c.page "Главная страница">
    <h1>Главная страница приложения!</h1>
    <#if countApp??>
        <h3>Количество приложений: ${countApp}</h3>
    </#if>
</@c.page>


