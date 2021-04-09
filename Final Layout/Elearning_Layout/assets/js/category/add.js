
//Hàm lấy ra tên file hình ảnh từ đường dẫn của máy local user để lưu xuống database
function getFileName() {
    var fullPath = document.getElementById('icon').value;
    if (fullPath) {
        var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
        var filename = fullPath.substring(startIndex);
        if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
            filename = filename.substring(1);
        }
        return filename;
    }
}

//Hàm Save ảnh xuống server
function saveIcon() {
    let iconInput = document.getElementById("icon");
    


    // ADD FILE VÀO ĐỐI TƯỢNG FORMDATA
    let formData = new FormData();
    formData.append('file', iconInput.files[0]);

    axios({
        url: 'http://localhost:8080/api/admin/file/category',
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

//Thêm 1 Category
function addCategory() {

    let flag = true;
    let titleInput = document.getElementById('title').value;
    let iconInput = document.getElementById('icon');
    if(titleInput.length == 0){
        flag = false ;
        document.getElementById('titleErr').innerHTML = "Vui lòng nhập tiêu đề !";


    }
    if(iconInput.files.length == 0){
        flag = false ;
        document.getElementById('iconErr').innerHTML = "Vui lòng chọn hình ảnh !";
    }


    if (flag === true) {
        // TẠO ĐỐI TƯỢNG USER
        let categoryDto = {
            "title" : titleInput,
            "icon" : getFileName()
        }

        // GỌI API THÊM MỚI
        axios({
            url: 'http://localhost:8080/api/admin/category',
            method: 'POST',
            data: categoryDto,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
              }
        })
            .then(function (resp) {
                console.log('Thành công! ' + resp.data);
                swal("Good job!", "Thêm Mới Thành Công!", "success");
                saveIcon();

            })
            .catch(function (err) {

                swal("Sorry", "Edit Thất Bại!", "error");
            })
    }
}


//Hàm sign out
function logout(){
    localStorage.removeItem('USER_TOKEN');
    location.replace("/login.html");
}