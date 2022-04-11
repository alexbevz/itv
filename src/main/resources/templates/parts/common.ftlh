<#macro page title>
    <!DOCTYPE html >
    <html lang="ru">
    <head>
        <meta charset="utf-8"/>
        <meta content="IE=edge" http-equiv="X-UA-Compatible"/>
        <meta content="width=device-width, initial-scale=1" name="viewport"/>

<#--        <link rel="stylesheet" href="@{/webjars/bootstrap/3.3.7/css/bootstrap.min.css}" type="text/css"/>-->
<#--        <link rel="stylesheet" href="@{/css/main.css}" type="text/css"/>-->
        <title>${title}</title>
    </head>
    <body>
    <div class="container">
        <#nested>
    </div>
<#--    <script th:src="@{/webjars/jquery/3.2.1/jquery.min.js/}" type="text/javascript"></script>-->
<#--    <script th:src="@{/webjars/bootstrap/3.3.7/js/bootstrap.min.js}" type="text/javascript"></script>-->
    </body>
    </html>
</#macro>