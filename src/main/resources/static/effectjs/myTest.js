

var inceptionStyleNet=null;
var mobileStyleNet=null;
window.onload=function init() {
    stylize=document.getElementById(stylize);
    stylize.onclick=stylize();

    loadMobileNetStyleModel().then(model => {
        this.styleNet = model;
    }).finally(() => this.enableStylizeButtons());
    alert("ok");
};
function stylize() {
    var a=1;
}


async function loadInceptionStyleModel() {
    if (!inceptionStyleNet) {
        inceptionStyleNet = await tf.loadFrozenModel(
            'saved_model_style_inception_js/tensorflowjs_model.pb',
            'saved_model_style_inception_js/weights_manifest.json');
    }

    return inceptionStyleNet;
}

async function loadMobileNetStyleModel() {
    if (!mobileStyleNet) {
        mobileStyleNet = await tf.loadFrozenModel(
            'saved_model_style_js/tensorflowjs_model.pb',
            'saved_model_style_js/weights_manifest.json');
    }

    return mobileStyleNet;
}
