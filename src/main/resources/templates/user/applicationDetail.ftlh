<#import "../parts/common.ftlh" as c>

<script src="https://www.google.com/jsapi"></script>

<script>
    google.load("visualization", "1", {packages: ["corechart"]});
    google.setOnLoadCallback(drawWmyChart);

    function drawWmyChart() {
        var data = google.visualization.arrayToDataTable([
            ['Название события', 'Неделя', 'Месяц', 'Год'],
            <#list wmyEvents as event>
            ['${event.name}', ${event.countLastWeek}, ${event.countLastMonth}, ${event.countLastYear}]<#if event_has_next>, </#if>
            </#list>
        ]);
        var options = {
            title: 'Диаграмма с разбивкой просмотров на неделю/месяц/год по названию событий',
            hAxis: {title: 'Названия событий'},
            vAxis: {title: 'Количество событий'},
            width: 1200,
            height: 700
        };
        var chart = new google.visualization.ColumnChart(document.getElementById('wmyChart'));
        chart.draw(data, options);
    }
</script>
<#if customEvents??>
    <script>
        google.load("visualization", "1", {packages: ["corechart"]});
        google.setOnLoadCallback(drawCustomPieChart);

        function drawCustomPieChart() {
            var data = google.visualization.arrayToDataTable([
                ['Название события', 'Количество событий'],
                <#list customEvents as event>
                ['${event.key}', ${event.value}]<#if event_has_next>, </#if>
                </#list>
            ]);
            var options = {
                title: 'Запросы по событиям',
                is3D: false,
                pieResidueSliceLabel: 'Остальное',
                width: 1200,
                height: 700
            };
            var chart = new google.visualization.PieChart(document.getElementById('pieChart'));
            chart.draw(data, options);
        }
    </script>
</#if>

<@c.page "Информация об приложении ${app.name}">
    <div class="row">
        <div class="col">
            <h2>Приложение: ${app.name}</h2>
            <h3>Уникальный идентификатор: ${app.id}</h3>

            <div class="row flex-nowrap">
                <div class="col-9">
                    <form method="get">
                        <input name="timeFrom" type="date" value="${timeFrom}"/>
                        -
                        <input name="timeTo" type="date" value="${timeTo}"/>
                        <button class="btn btn-primary" type="submit">Отобразить</button>
                        <#if timeError??>
                            <span class="alert alert-danger p-2">${timeError}</span>
                        </#if>
                    </form>
                </div>
                <div class="col-3">
                    <div class="btn-group d-flex justify-content-end" role="group">
                        <a class="btn btn-primary" href="/user/applications">Мои приложения</a>
                        <a class="btn btn-success" href="/csv/${app.filenameCsv}">Импорт</a>
                    </div>
                </div>
            </div>

            <div class="row">
                <div id="carouselExampleIndicators" class="carousel carousel-dark slide"
                     data-bs-ride="carousel">
                    <div class="carousel-indicators" style="bottom: -50px;">
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0"
                                class="active" aria-current="true" aria-label="Slide 1"></button>
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                                aria-label="Slide 2"></button>
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                                aria-label="Slide 3"></button>
                    </div>
                    <div class="carousel-inner" style="height: 700px;">
                        <div class="carousel-item active">
                            <#if wmyEvents?has_content>
                                <div id="wmyChart"></div>
                            <#else>
                                <strong>Диаграмму не получилось построить, так как нет данных!</strong>
                            </#if>
                        </div>
                        <div class="carousel-item">
                            <#if !timeError?? && customEvents?has_content>
                                <img src="/img/${app.filenameChart}" alt="${app.filenameChart}"/>
                            <#else>
                                <strong>Диаграмму не получилось построить, так как нет данных!</strong>
                            </#if>
                        </div>
                        <div class="carousel-item">
                            <#if !timeError?? && customEvents?has_content>
                                <div id="pieChart"></div>
                            <#else>
                                <strong>Диаграмму не получилось построить, так как нет данных!</strong>
                            </#if>
                        </div>
                    </div>
                    <button class="carousel-control-prev" style="left: -150px;" type="button"
                            data-bs-target="#carouselExampleIndicators"
                            data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" style="right: -150px;" type="button"
                            data-bs-target="#carouselExampleIndicators"
                            data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</@c.page>
