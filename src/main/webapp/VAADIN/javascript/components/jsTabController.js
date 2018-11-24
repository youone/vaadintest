se_you1_JsTabController = function() {

    // let self = this;

    let element = $(this.getElement());

    this.onStateChange = function() {

        this.tabId = this.getState().tabId;
        this.tabCaption = this.getState().title;
        console.log('state changed', this.getState());

        let old_element = $('span#tab_' + this.tabId).parent().parent()[0];
        let new_element = old_element.cloneNode(true);
        old_element.parentNode.replaceChild(new_element, old_element);

    //     $(this).off('click');
        $(new_element).on('click', event => {
            this.contentStorage.append(this.tabContent);
            console.log('changing tab', this.contentStorage, this.tabContent);
            // self.addPendingTab({id: self.tabId, caption: self.tabCaption});
        });

        // // setTimeout(() => {
        //     // console.log($('.v-caption-closable'));
        //     $('.v-caption-closable').each(function() {
        //         console.log($(this));
        //         // this.removeEventListener('click')
        //         // $(this).off();
        //         let old_element = this;
        //         let new_element = old_element.cloneNode(true);
        //         old_element.parentNode.replaceChild(new_element, old_element);
        //
        //     //     $(this).off('click');
        //         $(new_element).on('click', event => {
        //             self.contentStorage.append(self.tabContent);
        //             console.log('changing tab', self.contentStorage, self.tabContent);
        //             // self.addPendingTab({id: self.tabId, caption: self.tabCaption});
        //         });
        //     });

        this.tabContent = $('.tab_' + this.getState().tabId);
        this.contentStorage = this.tabContent.parent();
        // console.log('ADDING TAB', this.contentStorage);

        element.append(this.tabContent);
        // this.contentStorage.append(this.tabContent.clone(true));

        // }, 0);

    };

    this.addNewTab = function(tabId, caption) {
        console.log('STORING TAB CONTENT', this.tabContent);
        this.contentStorage.append(this.tabContent);
        this.addPendingTab({id: tabId, caption: caption});
    }

    this.changeTab = function(tabId, caption) {
        console.log('STORING TAB CONTENT', this.tabContent);
        this.contentStorage.append(this.tabContent);
        this.setTab({id: tabId, caption: caption});
    }


};