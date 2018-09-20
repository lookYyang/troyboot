'use strict';

/* 常用工具类库 */
var TB = function() {

	/**
	 * 序列化表单元素
	 * 
	 * @selector JQuery支持的元素选择器表达式
	 */
	var serialize = function(selector) {
		var formParams = {};
		$.each($(selector).serializeArray(), function(index, obj) {
			formParams[obj.name] = obj.value;
		});
		return formParams;
	}

	/**
	 * 表单校验
	 * TODO 需要进一步封装
	 * 
	 * @formSelector 表单选择符
	 * @submitHandler 表单提交响应事件
	 * @options 校验配置项
	 */
	var validate = function(formSelector, submitHandler, options) {
		$(formSelector).validate({
			errorElement : "em",
			// 校验规则
			rules : options.rules,
			// 提示信息
			messages : options.messages,

			highlight : function(element, errorClass, validClass) {
				$(element).closest('.field').addClass('has-error');
			},
			success : function(element, errorClass, validClass) {
				$(element).closest('.field').removeClass('has-error');
			},
			errorPlacement : function(error, element) {
				if (element.is(":radio") || element.is(":checkbox")) {
					element.closest('.option-group').after(error);
				} else {
					error.insertAfter(element);
				}
			},
			// 拦截并阻止原生submit事件，提供新的事件句柄接口
			submitHandler : submitHandler
		});
	}

	return {
        serialize : serialize,
		validate : validate
	}

}();