// default timeouts. when page is loaded checking sequence after 1000, then 3000 and so..
var timeouts = [1000,3000,10000];

var currentTimeoutIndex = 0;

function getMainForm() {
	return document.openlegacyForm;
}

function TerminalSession() {

	this.doAction = function(keyboardKey) {
		getMainForm().KeyboardKey.value = keyboardKey;
		getMainForm().submit();
	};
}

var terminalSession = new TerminalSession();

dojo.addOnLoad(function(){
	setFocus();
	attachFieldsFocus();
	setTimeout(checkSequence,timeouts[currentTimeoutIndex]);
});

function checkSequence(){
	var xhrArgs = {
		handleAs : "text",
		headers: { "Accept": "text/html;type=ajax" },
		url : "sequence",
		load : function(data) {
			
			if (Number(getMainForm().Sequence.value) != Number(data)){
				location.href = location.href;
			}
			else{
				// step to the next timeout
				if (currentTimeoutIndex+1 < timeouts.length){
					currentTimeoutIndex++;
				}
				setTimeout(checkSequence,timeouts[currentTimeoutIndex]);
			}
		},
		error : function(e) {
			alert(e);
		}
	}
	var deferred = dojo.xhrGet(xhrArgs);
}

function setFocus(){
    var elements = getMainForm().elements;
    var focusFieldName = getMainForm().TerminalCursor.value;
    
    var focusField = dojo.byId(focusFieldName);
    if (focusField != null){
    	if (focusField.focus != null) focusField.focus();
    }
}

function attachFieldsFocus(){
    var elements = getMainForm().elements;
    for (var i=0;i<elements.length;i++){
    	var element = elements[i];
    	dojo.connect(element, "onfocus", function(e) {
    		getMainForm().TerminalCursor.value = e.target.name; 
    	});
    }
}