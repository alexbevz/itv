<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="mb-3 sb-3">
            <label class="form-label">Электронная почта:</label>
            <input class="form-control ${usernameError???string('is-invalid', '')}"
                   name="username"
                   placeholder="Электронная почта"
                   type="<#if isRegisterForm>email<#else>text</#if>"
                   value="<#if user??>${user.username}</#if>"/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
        <div class="mb-3 sb-3">
            <label class="form-label">Пароль:</label>
            <input class="form-control ${passwordError???string('is-invalid', '')}"
                   name="password"
                   placeholder="Пароль"
                   type="password"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if isRegisterForm>
            <div class="mb-3 sb-3">
                <label class="form-label">Подтверждение пароля:</label>
                <input class="form-control ${confirmPasswordError???string('is-invalid', '')}"
                       name="confirmPassword"
                       placeholder="Подтверждение пароля"
                       type="password"/>
                <#if confirmPasswordError??>
                    <div class="invalid-feedback">
                        ${confirmPasswordError}
                    </div>
                </#if>
            </div>
            <div class="mb-3 sb-3">
                <#include "security.ftlh">
                <div class="g-recaptcha sb-3" data-sitekey="${captchaKey}"></div>
                <#if captchaError??>
                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
                </#if>
            </div>
        </#if>
        <div class="mb-3 sb-3">
            <#if !isRegisterForm>
                <div class="mb-3 sb-3">
                    <button class="btn btn-primary" type="submit">Войти</button>
                </div>
                <a href="/registration">Зарегистрироваться</a>
            <#else>
                <div class="mb-3 sb-3">
                    <button class="btn btn-primary" type="submit">Зарегистрироваться</button>
                </div>
                <a href="/login">Войти</a>
            </#if>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">Выйти</button>
    </form>
</#macro>
