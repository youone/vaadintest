se_you1_JsTabController = function() {

    let self = this;

    // if (!window.tabControllers) window.tabControllers = {};
    window.tabController = this;


    // this.element = $(this.getElement());
    // this.idElement = $('<div>');
    this.idElement = $('<div>');
    this.element = $('<div>');
    this.mainElement = $(this.getElement()).append(this.idElement).append(this.element);
    // this.element = $('<div>').appendTo(this.mainElement);

    this.onStateChange = function() {

        this.tabId = this.getState().tabId;
        this.tabCaption = this.getState().title;
        console.log('state changed', this.getState(), this.idElement);

        this.idElement.html(this.tabCaption);

        // window.tabControllers[this.tabId] = this;

        let old_element = $('span#tab_' + this.tabId).parent().parent()[0];
        let new_element = old_element.cloneNode(true);
        // old_element.parentNode.replaceChild(new_element, old_element);

        $(old_element).on('click', event => {
            console.log('USING CONTROLLER', window.tabController.tabId);

            let tabClose = $(event.currentTarget).find('.v-tabsheet-caption-close').first();
            let tabCaption = $(event.currentTarget).find('span').first();
            let tabId = tabCaption.attr('id').split('tab_')[1];

            if ($(event.target).attr('class') === 'v-tabsheet-caption-close') {
                // this.contentStorage.append(window.tabController.tabContent);
                window.tabController.closeTab({id: tabId});
                console.log('REMOVING', this.contentStorage.find('.tab_' + tabId));
                this.contentStorage.find('.tab_' + tabId).remove();
                console.log('CLOSING');
            }
            else {
                this.contentStorage.append(window.tabController.tabContent);
                window.tabController.changeTab({id: tabId});
                console.log('CHANGING');
            }

        });

        // $('.v-caption-closable').each(function() {
        //     let old_element = this;
        //     let new_element = old_element.cloneNode(true);
        //     old_element.parentNode.replaceChild(new_element, old_element);
        //
        //     $(new_element).on('click', event => {
        //         self.contentStorage.append(self.tabContent);
        //         console.log('changing tab', self.contentStorage, self.tabContent);
        //         self.changeTab({id: $(event.target).attr('id').split('tab_')[1]});
        //     });
        // });

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
        this.addPendingTab({id: tabId, caption: caption});
    }

    // this.changeTab = function(tabId, caption) {
    //     console.log('STORING TAB CONTENT', this.tabContent);
    //     this.contentStorage.append(this.tabContent);
    //     this.setTab({id: tabId, caption: caption});
    // }


};