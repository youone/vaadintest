console.log('tabs loaded');

// (function($){"use strict";var combinators=[" ",">","+","~"];var fraternisers=["+","~"];var complexTypes=["ATTR","PSEUDO","ID","CLASS"];function grok(msobserver){if(!$.find.tokenize){msobserver.isCombinatorial=true;msobserver.isFraternal=true;msobserver.isComplex=true;return}msobserver.isCombinatorial=false;msobserver.isFraternal=false;msobserver.isComplex=false;var token=$.find.tokenize(msobserver.selector);for(var i=0;i<token.length;i++){for(var j=0;j<token[i].length;j++){if(combinators.indexOf(token[i][j].type)!=-1)msobserver.isCombinatorial=true;if(fraternisers.indexOf(token[i][j].type)!=-1)msobserver.isFraternal=true;if(complexTypes.indexOf(token[i][j].type)!=-1)msobserver.isComplex=true}}}var MutationSelectorObserver=function(selector,callback,options){this.selector=selector.trim();this.callback=callback;this.options=options;grok(this)};var msobservers=[];msobservers.initialize=function(selector,callback,options){var seen=[];var callbackOnce=function(){if(seen.indexOf(this)==-1){seen.push(this);$(this).each(callback)}};$(options.target).find(selector).each(callbackOnce);var msobserver=new MutationSelectorObserver(selector,callbackOnce,options);this.push(msobserver);var observer=new MutationObserver(function(mutations){var matches=[];function push(match){matches.push(match)}for(var m=0;m<mutations.length;m++){if(mutations[m].type=="attributes"){if(mutations[m].target.matches(msobserver.selector))matches.push(mutations[m].target);if(msobserver.isFraternal)mutations[m].target.parentElement.querySelectorAll(msobserver.selector).forEach(push);else mutations[m].target.querySelectorAll(msobserver.selector).forEach(push)}if(mutations[m].type=="childList"){for(var n=0;n<mutations[m].addedNodes.length;n++){if(!(mutations[m].addedNodes[n]instanceof Element))continue;if(mutations[m].addedNodes[n].matches(msobserver.selector))matches.push(mutations[m].addedNodes[n]);if(msobserver.isFraternal)mutations[m].addedNodes[n].parentElement.querySelectorAll(msobserver.selector).forEach(push);else mutations[m].addedNodes[n].querySelectorAll(msobserver.selector).forEach(push)}}}matches.forEach(function(match){$(match).each(msobserver.callback)})});var defaultObeserverOpts={childList:true,subtree:true,attributes:msobserver.isComplex};observer.observe(options.target,options.observer||defaultObeserverOpts)};$.fn.initialize=function(callback,options){msobservers.initialize(this.selector,callback,$.extend({},$.initialize.defaults,options))};$.initialize=function(selector,callback,options){msobservers.initialize(selector,callback,$.extend({},$.initialize.defaults,options))};$.initialize.defaults={target:document.documentElement,observer:null}})(jQuery);
// $(".v-tabsheet-scroller").initialize( function(){
//     console.log("initializing");
//     $(this).attr("title", "tooltip");
//     $(this).qtip({
//         show : {
//             ready : true, // show when created on orderError call
//             when : false // never show unless explicitly called
//         },
//         hide: {
//             fixed: true,
//             delay: 300
//         },
//         position: {
//             my: "top right",
//             at: "top"
//         }
//     });
//
// });


$.widget( "custom.catcomplete", $.ui.autocomplete, {
    _create: function() {
        this._super();
        this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
    },
    _renderMenu: function( ul, items ) {
        var that = this,
            currentCategory = "";
        $.each( items, function( index, item ) {


            var li;
            if ( item.category != currentCategory ) {
                ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
                currentCategory = item.category;
            }

            li = that._renderItemData(ul, item);

            if ( item.category ) {
                li.attr( "aria-label", item.category + " : " + item.label );
            }
        });
    },
    _renderItem: function( ul, item ) {
        return $( "<li>" )
            .append($("<div>").html(item.label))
            .appendTo( ul );
    },

});

let data = [
    { label: "anders", category: "" },
    { label: "andreas", category: "" },
    { label: "antal", category: "" },
    { label: "annhhx10", category: "Products" },
    { label: "annk K12", category: "Products" },
    { label: "annttop C13", category: "Products" },
    { label: "anders andersson", category: "People" },
    { label: "andreas andersson", category: "People" },
    { label: "andreas johnson", category: "People" }
];

$(document).on("mouseenter", ".v-tabsheet-scroller", function(event){

    console.log($(this).parent());
    console.log($(event.currentTarget));

    $($(this).parent()).find(".v-tabsheet-tabitemcell").each(function(index, ui){
        $(ui).find(".v-captiontext").each(function(a,b) {
            console.log($(b).html());
        });
    });

    $(this).qtip();
    $(".add-tab").attr("title", "tooltip");
    $(".add-tab").qtip();
    $(this).attr("title", "tooltip");
    $(this).qtip({
        show : {
            ready : true, // show when created on orderError call
            when : false // never show unless explicitly called
        },
        hide: {
            fixed: true,
            delay: 300
        },
        position: {
            my: "top right",
            at: "top"
        }
    });

    selectTab(["tabName"]);




});

$(document).ready(function(){
    $("<input>")
        .catcomplete({
            delay: 0,
            source: data
        })
        .prependTo("body");
});

