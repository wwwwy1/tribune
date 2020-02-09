//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
        $.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            errorElement: "span",
            errorPlacement: function (error, element) {
                if (element.is(":radio") || element.is(":checkbox")) {
                    error.appendTo(element.parent().parent().parent());
                } else {
                    error.appendTo(element.parent());
                }
            },
            errorClass: "help-block m-b-none",
            validClass: "help-block m-b-none"
        });

        //以下为官方示例
        $().ready(function () {
            // validate the comment form when it is submitted
            $("#commentForm").validate();

            // validate signup form on keyup and submit
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({

                rules: {
                    firstname: "required",
                    lastname: "required",
                    userName: {
                        required: true,
                        minlength: 2
                    },
                    password: {
                        required: function (){
                            if ($("#userId").val() != -1){
                                return false;
                            }else {
                                return true;
                            }
                        },
                        minlength: 5
                    },
                    confirm_password: {
                        required: function (){
                            if ($("#userId").val() != -1){
                                return false;
                            }else {
                                return true;
                            }
                        },
                        minlength: 5,
                        equalTo: "#password"
                    },
                    userEmail: {
                        required:true,
                        email: true
                    },
                    topic: {
                        required: "#newsletter:checked",
                        minlength: 2
                    },
                    agree: "required"
                },
                messages: {
                    firstname: icon + "请输入你的姓",
                    lastname: icon + "请输入您的名字",
                    userName: {
                        required: icon + "请输入您的用户名",
                        minlength: icon + "用户名必须两个字符以上"
                    },
                    password: {
                        required:icon + "请输入您的密码",
                        minlength: icon + "密码必须5个字符以上"
                    },
                    confirm_password: {
                        required:icon + "请输入您的密码",
                        minlength: icon + "密码必须5个字符以上",
                        equalTo: icon + "两次输入的密码不一致"
                    },
                    userEmail: icon + "请输入您的E-mail",
                    agree: {
                        required: icon + "必须同意协议后才能注册",
                        element: '#agree-error'
                    }
                },submitHandler: function (form) {
                        var id = $("#userId").val();
                        if (id == -1)
                            id = null;
                        $.ajax({
                            url: "/user/add",
                            data:{"userId":id,"userName":$("#userName").val(),"userNickname":$("#userNickname").val(),"userTitle":$("#userTitle").val(),
                                "userPassword":$("#password").val(),"userEmail":$("#userEmail").val()},
                            type: "post",
                            dataType: "json",
                            success: function (data) {
                                if (data.code == 200){
                                    swal({
                                        title: "添加成功",
                                        text: "添加成功",
                                        type: "success"
                                    },function () {
                                        location.href("/user/page?current="+1+"&pageSize="+10);
                                    });
                                } else if (data.code == 201){
                                    swal({
                                        title: "修改成功",
                                        text: "修改成功",
                                        type: "success"
                                    },function () {
                                        location.href("/user/page?current="+1+"&pageSize="+10);
                                    });
                                } else {
                                    swal({
                                        title: "添加失败",
                                        text: "添加失败",
                                        type: "warning"
                                    },function () {
                                        location.href("/user/page?current="+1+"&pageSize="+10);
                                    });
                                }
                            }
                        })
                    }
            });

            // propose username by combining first- and lastname
            $("#username").focus(function () {
                var firstname = $("#firstname").val();
                var lastname = $("#lastname").val();
                if (firstname && lastname && !this.value) {
                    this.value = firstname + "." + lastname;
                }
            });
        });
