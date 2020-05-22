<!--
var arrText = new Array(5);
var arrValue = new Array(arrText.length);

function objSetOption(select1, select2, select3) {
    this.select1 = select1;
    this.select2 = select2;
    this.select3 = select3;
}

arrText[0]= new objSetOption("省份:省份", "地级市:地级市", "市、县级:市、县级");
arrText[1] = new objSetOption("北京市:北京市", "北京市:北京市", "东城区:东城区,西城区:西城区,崇文区:崇文区,宣武区:宣武区,朝阳区:朝阳区,丰台区:丰台区,石景山区:石景山区,海淀区:海淀区,门头沟区:门头沟区,房山区:房山区,通州区:通州区,顺义区:顺义区,昌平区:昌平区,大兴区:大兴区,怀柔区:怀柔区,平谷区:平谷区,密云县:密云县,延庆县:延庆县,延庆镇:延庆镇");
arrText[2] = new objSetOption("天津市:天津市", "天津市:天津市", "黄浦区:黄浦区,卢湾区:卢湾区,徐汇区:徐汇区,静安区:静安区,静安区:静安区,普陀区:普陀区,闸北区:闸北区,虹口区:虹口区,杨浦区:杨浦区,闵行区:闵行区,宝山区:宝山区,嘉定区:嘉定区,浦东新区:浦东新区,金山区:金山区,松江区:松江区,青浦区:青浦区,南汇区:南汇区,奉贤区:奉贤区,崇明县:崇明县,城桥镇:城桥镇");
arrText[3] = new objSetOption("显示c:值c", "显示c2_1:值c2_1,显示c2_2:值c2_2", "显示c3_1:值c3_1,显示c3_2:值c3_2");
arrText[4] = new objSetOption("显示d:值d", "显示d2_1:值d2_1,显示d2_2:值d2_2", "显示d3_1:值d3_1,显示d3_2:值d3_2");
arrText[5] = new objSetOption("显示e:值e", "显示e2_1:值e2_1,显示e2_2:值e2_2", "显示e3_1:值e3_1,显示e3_2:值e3_2");

function select(sValue1, sValue2, sValue3) {
    var eltSelect1 = document.getElementById("s_province");
    var eltSelect2 = document.getElementById("s_city");
    var eltSelect3 = document.getElementById("s_county");
    var arrSelect1, arrSelect2, arrSelect3;
    var arrData1, arrData2, arrData3;
    with(eltSelect1) {
        var strSelect = options[selectedIndex].value;
    }
    for(i = 0;i < arrText.length;i ++) {
        arrSelect1 = arrText[i].select1;
        arrData1 = arrSelect1.split(":");
        if (arrData1[1] == strSelect) {
            arrSelect2 = (arrText[i].select2).split(",");
            for(j = 0;j < arrSelect2.length;j++) {
                arrData2 = arrSelect2[j].split(":");
                with(eltSelect2) {
                    length = arrSelect2.length;
                    options[j].text = arrData2[0];
                    options[j].value = arrData2[1];
                    if (arrData2[1] == sValue2) {
                        options[j].selected = true;
                    }
                }
            }
            arrSelect3 = (arrText[i].select3).split(",");
            for(j = 0;j < arrSelect3.length;j++) {
                arrData3 = arrSelect3[j].split(":");
                with(eltSelect3) {
                    length = arrSelect3.length;
                    options[j].text = arrData3[0];
                    options[j].value = arrData3[1];
                    if (arrData3[1] == sValue3) {
                        options[j].selected = true;
                    }
                }
            }
            break;
        }
    }
}

function init(sValue1, sValue2, sValue3) {
    var eltSelect1 = document.getElementById("s_province");
    var eltSelect2 = document.getElementById("s_city");
    var eltSelect3 = document.getElementById("s_county");
    var arrSelect1, arrSelect2, arrSelect3;
    var arrData1, arrData2, arrData3;
    if (eltSelect1 != undefined && eltSelect2 != undefined && eltSelect3 != undefined) {
        with(eltSelect2) {
            arrSelect2 = (arrText[0].select2).split(",");
            length = arrSelect2.length;
            for(i = 0;i < length;i ++) {
                arrData2 = arrSelect2[i].split(":");
                options[i].text = arrData2[0];
                options[i].value = arrData2[1];
            }
        }
        with(eltSelect3) {
            arrSelect3 = (arrText[0].select3).split(",");
            length = arrSelect3.length;
            for(i = 0;i < length;i ++) {
                arrData3 = arrSelect3[i].split(":");
                options[i].text = arrData3[0];
                options[i].value = arrData3[1];
            }
        }
        with(eltSelect1) {
            length = arrText.length;
            for(i = 0;i < arrText.length;i ++) {
                arrSelect1 = arrText[i].select1;
                arrData1 = arrSelect1.split(":");
                options[i].text = arrData1[0];
                options[i].value = arrData1[1];
                if (arrData1[1] == sValue1) {
                    options[i].selected = true;
                    select("", sValue2, sValue3);
                }
            }
        }
    }
}

//init();
//默认初始化


// -->