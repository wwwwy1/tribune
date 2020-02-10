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
                    tagsName: "required",
                    tagsRemarks: "required"
                },
                messages: {
                    tagsName: icon + "请输入标签名",
                    tagsRemarks: icon + "请输入标签的说明"
                },submitHandler: function (form) {
                        var id = $("#tagsId").val();
                        if (id == -1)
                            id = null;
                        var isEnable = 0;
                        if($('#tagsEnable').prop('checked')) isEnable = 1;
                        $.ajax({
                            url: "/tags/add",
                            data:{"id":id,"tagsName":$("#tagsName").val(),"tagsRemarks":$("#tagsRemarks").val(),"tagsEnable":isEnable},
                            type: "post",
                            dataType: "json",
                            success: function (data) {
                                if (data.code == 200){
                                    swal({
                                        title: "添加成功",
                                        text: "添加成功",
                                        type: "success"
                                    },function () {
                                        location.href("/tags/page?current="+1+"&pageSize="+10);
                                    });
                                } else if (data.code == 201){
                                    swal({
                                        title: "修改成功",
                                        text: "修改成功",
                                        type: "success"
                                    },function () {
                                        location.href("/tags/page?current="+1+"&pageSize="+10);
                                    });
                                } else if (data.code == 202){
                                    swal({
                                        title: "新增失败",
                                        text: "标签名重复",
                                        type: "warning"
                                    });
                                }else {
                                    swal({
                                        title: "添加失败",
                                        text: "添加失败",
                                        type: "warning"
                                    },function () {
                                        location.href("/tags/page?current="+1+"&pageSize="+10);
                                    });
                                }
                            }
                        })
                    }
            });
        });
