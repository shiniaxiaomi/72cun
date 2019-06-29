javascript:(function (x, sc, width, height) {
    var xhr = new XMLHttpRequest();
    xhr.open('get', 'http://127.0.0.1:8087/url/addUrlFaster?url='+encodeURIComponent(location.href)+'&label='+encodeURIComponent(x.title)+'&mark=加密后的id', true);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        if (status == 200) {
            if(xhr.response=null){
                alert('请先登入!')
            }else{
                if(xhr.response.code==0){
                    alert(xhr.response.message)
                }else{
                    alert('发生错误，请稍后再试!')
                }
            }
        } else {
            alert('发生错误，请稍后再试!')
        }
    };
    xhr.send();
})(document, screen, 1420, 760);
