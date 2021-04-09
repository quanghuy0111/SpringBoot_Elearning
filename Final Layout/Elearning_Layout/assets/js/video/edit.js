function loadCourse() {
    axios({
        url: 'http://localhost:8080/api/admin/course',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //  Lấy ra mảng role
            let arrCourse = resp.data;
            // Tạo danh sách thẻ option
            let strOption = "";
            for (let courseDto of arrCourse) {
                strOption += `<option value="${courseDto.id}">${courseDto.title}</option>`;
            }
            // Truy cập tới thẻ select có id là 'roleId'
            let courseIdTag = document.getElementById('courseId');
            // Thay thế các thẻ option cũ bằng danh sách thẻ option mới
            courseIdTag.innerHTML = strOption;
        })
        .catch(function (err) {
            console.log(err.response);
            

        })
}
loadCourse();

function getFileName() {
    var fullPath = document.getElementById('image').value;
    if (fullPath) {
        var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
        var filename = fullPath.substring(startIndex);
        if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
            filename = filename.substring(1);
        }
        return filename;
    }
}

function saveIcon() {
    let iconInput = document.getElementById("image");
    // KIỂM TRA XEM CHỌN HÌNH CHƯA


    // ADD FILE VÀO ĐỐI TƯỢNG FORMDATA
    let formData = new FormData();
    formData.append('file', iconInput.files[0]);

    axios({
        url: 'http://localhost:8080/api/admin/file/upload/video',
        method: 'POST',
        data: formData,
        headers: {
            'Content-Type': 'multipart/form-data',
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")

            
        }
    })
        .then(function (resp) {
            console.log(resp.data);
        })
        .catch(function (err) {
            console.log(err)
        })

}

function editVideo() {

    let flag = true;
    const queryString = window.location.search;

    const urlParams = new URLSearchParams(queryString);

    const idVideo = urlParams.get('id');

    let titleInput = document.getElementById('title').value;
    if(titleInput.length == 0){
        flag = false;
        document.getElementById('titleError').innerHTML = 'Vui lòng không để trống tiêu đề';
    }
    let urlInput = document.getElementById('url').value;
    if(urlInput.length == 0){
        flag = false;
        document.getElementById('urlError').innerHTML = 'Vui lòng không để trống url';
    }
    let timeCountInput = document.getElementById('timeCount').value;
    if(timeCountInput.length == 0){
        flag = false;
        document.getElementById('timeCountError').innerHTML = 'Vui lòng không để trống thời lượng';
    }
    let iconInput = document.getElementById("image");
    if(iconInput.files.length == 0){
        flag = false;
        document.getElementById('iconError').innerHTML = 'Vui lòng chọn file';
    }
    let courseInput = document.getElementById('courseId').value;


    if (flag === true) {
        // TẠO ĐỐI TƯỢNG USER
        let videoDto = {
            "id":idVideo,
            "title" : titleInput,
            "url":urlInput,
            "image" : getFileName(),
            "timeCount": timeCountInput,
            "courseId": courseInput
            
        }

        // GỌI API THÊM MỚI
        axios({
            url: `http://localhost:8080/api/admin/video/${idVideo}`,
            method: 'PUT',
            data: videoDto,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
              }
        })
            .then(function (resp) {
                console.log('Thành công! ' + resp.data);
                swal("Good job!", "Edit Thành Công!", "success");
                saveIcon();

            })
            .catch(function (err) {

                swal("Sorry", "Edit Thất Bại!", "error");
            })
    }
}
function logout(){
    localStorage.removeItem('USER_TOKEN');
    location.replace("/login.html");
}