

!function (e, t, o) {
    "use strict";
    !function () {
        o("#exampleTableEvents").bootstrapTable({
            url: "/user/myCourse.action",
            search: true,
            pagination: true,
            showRefresh: true,
            showToggle: true,
            showColumns: true,
            showExport: true,
            exportDataType: 'all',
            exportTypes: ['json', 'xml', 'csv', 'txt', 'sql', 'excel','XLSX','pdf'],
            exportOptions:{
                fileName: "data"
            },
            buttonsClass:"default",
            iconSize: "outline",
            toolbar: "#exampleTableEventsToolbar",
            icons: {refresh: "glyphicon-repeat", toggle: "glyphicon-list-alt", columns: "glyphicon-list",export:"glyphicon-export icon-share"},
        });
    }()
}(document, window, jQuery);
