se_you1_JsTabController = function() {

    // let self = this;

    window.currentTabController = this;

    this.idElement = $('<div>');
    this.element = $('<div>');
    this.mainElement = $(this.getElement()).append(this.idElement).append(this.element);

    this.onStateChange = function() {

        this.tabId = this.getState().tabId;
        this.tabCaption = this.getState().title;
        console.log('state changed', this.getState(), this.idElement);

        this.idElement.html(this.tabCaption);

        let old_element = $('span#tab_' + this.tabId).parent().parent()[0];
        // let new_element = old_element.cloneNode(true);
        // old_element.parentNode.replaceChild(new_element, old_element);

        $(old_element).on('click', event => {
            console.log('USING CONTROLLER', window.currentTabController.tabId);

            let tabClose = $(event.currentTarget).find('.v-tabsheet-caption-close').first();
            let tabCaption = $(event.currentTarget).find('span').first();
            let tabId = tabCaption.attr('id').split('tab_')[1];

            if ($(event.target).attr('class') === 'v-tabsheet-caption-close') {
                console.log('CLOSING TAB');
                // window.currentTabController.closeTab({id: tabId});
                this.contentStorage.find('.tab_' + tabId).remove();
            }
            else {
                console.log('CHANGING TAB');
                this.contentStorage.append(window.currentTabController.tabContent);
                // window.currentTabController.changeTab({id: tabId});
            }

        });

        this.tabContent = $('.tab_' + this.getState().tabId).parent();
        this.tabContent.addClass('tab-content');
        this.contentStorage = this.tabContent.parent();
        this.contentStorage.addClass('tab-content-storage');
        // this.contentStorage.parent().parent().css('display', 'none');
        // console.log('ADDING TAB', this.contentStorage);

        this.element.append(this.tabContent);
        // this.contentStorage.append(this.tabContent.clone(true));

        // }, 0);

    };

    this.addNewTab = function(tabId, caption) {
        console.log('STORING TAB CONTENT', this.tabContent);
        this.contentStorage.append(this.tabContent);
        this.addTab({id: tabId, caption: caption});
    }

    // this.changeTab = function(tabId, caption) {
    //     console.log('STORING TAB CONTENT', this.tabContent);
    //     this.contentStorage.append(this.tabContent);
    //     this.setTab({id: tabId, caption: caption});
    // }


};