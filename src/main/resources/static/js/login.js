require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min',
        // xcConfirm:'plugins/confirm/xcConfirm'
    }
});

require(['jquery', 'manage/js/requestData', 'manage/js/initLogin'],
    function ($, RequestData, initLogin) {
    });