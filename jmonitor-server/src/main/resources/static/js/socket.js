var interval_time = 6000;
var appId = GetQueryString("appId");
var number = GetQueryString("number");

// Socket
function gc() {
    var socket = new SockJS('/websocket');
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/gc', function (d) {
            var data = JSON.parse(d.body)
            s0(data)
            s1(data)
            eden(data)
            old(data)
            mc(data)
            ccsc(data)
            gcn(data)
            gct(data)
            setTimeout(function () {
                stompClient.send("/app/gc", {}, JSON.stringify({"appId": appId, "number": number}));
            }, interval_time);
        });
        stompClient.send("/app/gc", {}, JSON.stringify({"appId": appId, "number": number}));
    });
}

//链接
gc()
// cl()
// thread()

//设置频率
$("#pinlv").click(function () {
    //prompt层
    layer.prompt({title: '请设置图表刷新频率,单位/秒'}, function (pass, index) {
        layer.close(index);
        interval_time = pass * 1000;
        layer.msg('设置成功！刷新频率：' + pass + '秒')
    });
});