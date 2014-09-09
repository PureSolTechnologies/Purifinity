function processAjaxStatus(msgId) {
	function processEvent(data) {
		var msg = document.getElementById(msgId);
		if (data.status == "begin") {
			msg.style.display = '';
		} else if (data.status = "success") {
			msg.style.display = '';
		}
	}
	return processEvent;
}

function registerAjaxStatus(msgId) {
	jsf.ajax.addOnEvent(processAjaxStatus(msgId));
}
