//Hàm cho phép edit
function enableEdit(code) {
    if (code == 1) {
        document.getElementById('title').readOnly = false;
        document.getElementById('title').value = "";
    }
    if (code == 2) {
        document.getElementById('icon').disabled = false;
        document.getElementById('icon').value = "";

    }
}

//Vì hiện tại em chưa biết làm thế nào để save hình ảnh cùng đối tượng nên tạm thời em lấy tên image để save vào đối tượng
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

//Hàm thực hiện chức năng upload hình ảnh lên server
function saveIcon() {
    let iconInput = document.getElementById("icon");
    // KIỂM TRA XEM CHỌN HÌNH CHƯA


    // ADD FILE VÀO ĐỐI TƯỢNG FORMDATA
    let formData = new FormData();
    formData.append('file', iconInput.files[0]);

    //Gọi API upload hình ảnh
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


//Hàm sửa category
function editCate() {

    var flag = true;

    let iconInput = document.getElementById("icon");



    // ADD FILE VÀO ĐỐI TƯỢNG FORMDATA
    let formData = new FormData();
    formData.append('file', iconInput.files[0]);
    
    //Lấy ra tham số của URL
    const queryString = window.location.search;

    const urlParams = new URLSearchParams(queryString);

    const idCate = urlParams.get('id');
    var titleInput = document.getElementById('title').value;



    //Lấy ra danh sách các element thuộc class form-control
    var slides = document.getElementsByClassName('form-control');
    

    var categoryDto = {
        "id": idCate,

    }


    //Chạy vòng lặp , để xét từng element trong danh sách c
    for (var i = 0; i < slides.length; i++) {
        if (slides.item(i).readOnly === false) {


            if (slides.item(i).getAttribute('id') === 'title') {

                if (titleInput.length == 0) {
                    flag = false;
                    document.getElementById('titleErr').innerHTML = 'Vui lòng nhập tiêu đề!';
                }

                categoryDto.title = titleInput;

            }


            else if (slides.item(i).getAttribute('id') === 'icon') {
                
                if (iconInput.files.length === 0) {
                    document.getElementById('iconErr').innerHTML = 'Vui lòng không bỏ trống icon!';
                    flag = false;


                }

                saveIcon();
                categoryDto.icon = getFileName();
            }

        }

    }
    if (flag == true) {
        axios({
            url: `http://localhost:8080/api/admin/category/${idCate}`,
            method: 'PUT',
            data: categoryDto,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
              }
        })
            .then(function (resp) {
                console.log('Thành công');

                
                swal("Good job!", "Edit Thành Công!", "success");
                disableEdit();
            })
            .catch(function (err) {
                console.log('lỗi xảy ra :' + console.error());
                console.log(idCate);
                swal("Sorry", "Edit Thất Bại!", "error");
            })
    }


    // Enable các trường edit

    function disableEdit() {

        document.getElementById('title').readOnly = true;
        document.getElementById('title').value = "";


        document.getElementById('icon').disabled = true;
        document.getElementById('icon').value = "";

    }
}

//Hàm sign out
function logout(){
    localStorage.removeItem('USER_TOKEN');
    location.replace("/login.html");
}