<#macro login>
    <form action="/login" method="post">
<#--        <#if param.invalidData>-->
<#--            <div class="alert alert-danger">-->
<#--                Ввведена неправильная электронная почта или пароль.-->
<#--            </div>-->
<#--        </#if>-->
<#--        <#if param.logout>-->
<#--            <div class="alert alert-info">-->
<#--                Вы успешно разавторизовались.-->
<#--            </div>-->
<#--        </#if>-->
        <div class="form-group">
            <label>
                Электронная почта:
                <input autofocus="autofocus"
                       class="form-control"
                       name="username"<#--TODO: to fix if will be a bug-->
                       placeholder="Электронная почта"
                       type="email"/>
            </label>
        </div>
        <div class="form-group">
            <label>
                Пароль:
                <input class="form-control"
                       name="password"
                       placeholder="Пароль"
                       type="password"/>
            </label>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group">
            <input class="form-control btn btn-info"
                   name="login-submit"
                   type="submit"
                   value="Войти"/>
        </div>
    </form>
</#macro>

<#macro registration>
    <form action="/registration" method="post">
        <div class="form-group">
            <label>
                Электронная почта:
                <input autofocus="autofocus"
                       class="form-control"
                       name="email"
                       placeholder="Электронная почта"
                       type="email"/>
            </label>
        </div>
        <div class="form-group">
            <label>
                Пароль:
                <input class="form-control"
                       name="password"
                       placeholder="Пароль"
                       type="password"/>
            </label>
        </div>
        <div class="form-group">
            <label>
                Подтверждение пароля:
                <input class="form-control"
                       name="confirmPassword"
                       placeholder="Подтверждение пароля"
                       type="password"/>
            </label>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group">
            <input class="form-control btn btn-info"
                   name="login-submit"
                   type="submit"
                   value="Зарегистрироваться"/>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Выйти"/>
    </form>
</#macro>