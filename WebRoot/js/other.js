
    $(document).ready(function() {
        $('#session').login();
        $('input, textarea').placeholder();
        $('input.input-error,textarea.input-error').keyup(function() {
            $('.text-error', $(this).removeClass('input-error').parent()).remove();
        });
        $('.has-dropdown > a').click(function() {
            var $item = $(this).parent();
            $("#site-nav-btn").removeClass('active');
            $('.site-nav').addClass('hidden-xs');
            $(".has-dropdown").not($item).removeClass('active');
            $item.toggleClass('active');
            return false;
        });
        $('html').click(function() {
            $(".has-dropdown, #site-nav-btn").removeClass('active');
            $('.site-nav').addClass('hidden-xs');
        });
        $('#site-nav-btn').click(function() {
            $(".has-dropdown").removeClass('active');
            $(this).toggleClass('active');
            $('.site-nav').toggleClass('hidden-xs');
            return false;
        });
        $(document).scroll(function() {
            if ($(this).scrollTop() > 720) {
                $('#backtop').removeClass('hidden');
            } else {
                $('#backtop').addClass('hidden');
            }
        });
        $('#backtop').click(function() {
            $('body,html').animate({
                scrollTop: 0
            });
            return false;
        }) ;
		$('#msg-link').eventPopup({
            url: 'http://x.segmentfault.com/event'
        });
        $('.meta-tags a, .tag').tagPopup('http://segmentfault.com/api/tag', '#main');
        $('#search .input-search').searchAutoComplete({
            url: 'http://x.segmentfault.com/autocomplete',
            insertAfter: '#search',
            searchUrl: 'http://segmentfault.com/search',
            askUrl: 'http://segmentfault.com/ask',
            ask: '#top-nav a.btn-m'
        });
        $('button.close', $('.top-alert').fadeIn()).click(function() {
            $(this).parents('.top-alert').fadeOut(function() {
                $(this).remove();
            });
            return false;
        });
        $('a.msg-close', $('#msg-bar').fadeIn().sticky()).click(function() {
            $(this).parent().fadeOut(function() {
                $(this).parent().remove();
            });
            return false;
        });
        var topNav = $('.head-nav'),
        search = $('#search .input-search').css({
            'position': 'relavtive',
            'z-index': 10
        }),
        searchWidth = search.outerWidth();
        search.focus(function() {
            search.animate({
                width: searchWidth * 1.5
            },
            'fast');
            topNav.hide();
        }).blur(function() {
            if (0 == search.val().length) {
                search.animate({
                    width: searchWidth
                },
                'fast',
                function() {
                    topNav.show();
                });
            }
        });
        $('.tip-pop').each(function() {
            var t = $(this),
            str = t.data('tip');
            if ( !! !str) {
                return;
            }
            t.removeAttr('title');
            var parts = str.split(':');
            $(this).tipsy({
                'html': true,
                'gravity': parts.shift(),
                'fallback': parts.join(':')
            });
        });
        if ($('#profile-tab,.greeting').length > 0) {
            $.get('http://x.segmentfault.com/news',
            function(o) {
                if (!o.status && o.data) {
                    var cv = $.cookie('sfns_viewed'),
                    viewed = ( !! cv ? cv + ',': '');
                    if (viewed.indexOf(o.data[0]) < 0) {
                        var a = $('<a href="' + o.data[1] + '" target="_blank" class="update-log rounded-2">' + '<strong>' + o.data[3] + '更新</strong> ' + o.data[2] + '</a>').prependTo('#secondary').animate({
                            backgroundColor: '#ffebb7'
                        },
                        'slow',
                        function() {
                            $(this).animate({
                                backgroundColor: '#FFF7E2'
                            },
                            'slow');
                        }).click(function() {
                            $.cookie('sfns_viewed', viewed + o.data[0], {
                                path: '/',
                                expires: 30
                            });
                            $(this).remove();
                        });
                    }
                }
            },
            'jsonp');
        }
        if (login) {
            $('#main, .layout-main').highlightTag({
                url: 'http://x.segmentfault.com/tag/following',
                selector: '#content article .meta-tags li a, #user-question article .meta-tags li a,' + ' .article-item .tags li a',
                className: 'q-highlight'
            });
        } else if (0 == $('.auth-login,.session-form,.session-finished').length) {
            if (1 != $.cookie('sfln_viewed')) {
                $.cookie('sfln_viewed', 1, {
                    path: '/'
                });
                $.cookie('sfln_available', 1, {
                    path: '/'
                });
            } else if (1 == $.cookie('sfln_available')) {
                $('.i-cancel', $('.login-notify').css('bottom', -60).removeClass('hidden').animate({
                    'bottom': 0
                })).click(function() {
                    $.cookie('sfln_available', 0, {
                        path: '/'
                    });
                    $(this).parents('.login-notify').animate({
                        'bottom': -60
                    },
                    function() {
                        $(this).remove();
                    });
                    return false;
                });
            }
        }
        var tagInterestShow = false;
        $('.tag-interest-click').click(function() {
            if (!tagInterestShow) {
                $('.tag-interest-list').fadeIn();
                tagInterestShow = true;
            } else {
                $('.tag-interest-list').fadeOut();
                tagInterestShow = false;
            }
            return false;
        });
        function hotkeySubmit1(arg) {
            $(arg.parents).on('focus', arg.textArea,
            function() {
                var t = $(this).closest(arg.scope);
                Mousetrap.bind(arg.hotkey,
                function() {
                    $(arg.submitBtn, t).trigger('click');
                });
            }).on('blur', '.mousetrap',
            function() {
                Mousetrap.bind(arg.hotkey,
                function() {
                    return false;
                });
            });
        }
        hotkeySubmit1({
            parents: '.write-event-comment',
            textArea: '.mousetrap',
            scope: 'form',
            hotkey: ['command+enter', 'ctrl+enter'],
            submitBtn: 'button[type = submit]'
        });
        hotkeySubmit1({
            parents: '.write-event-comment',
            textArea: '.mousetrap',
            scope: 'form',
            hotkey: ['command+enter', 'ctrl+enter'],
            submitBtn: 'button[type = submit]'
        });
        hotkeySubmit1({
            parents: '.write-content',
            textArea: '#wmd-input',
            scope: 'body',
            hotkey: ['command+enter', 'ctrl+enter'],
            submitBtn: '.action'
        });
        hotkeySubmit1({
            parents: '#blog-main',
            textArea: '#comment-text',
            scope: 'form',
            hotkey: ['command+enter', 'ctrl+enter'],
            submitBtn: 'button[type = submit]'
        });
        hotkeySubmit1({
            parents: '.comment-list',
            textArea: '.add-comment-text',
            scope: '.add-comment',
            hotkey: ['command+enter', 'ctrl+enter'],
            submitBtn: 'button[type = submit]'
        });
        hotkeySubmit1({
            parents: '#write-answer',
            textArea: '#wmd-input',
            scope: 'form',
            hotkey: ['command+enter', 'ctrl+enter'],
            submitBtn: 'input[type = submit]'
        });
        hotkeySubmit1({
            parents: '#question',
            textArea: '.add-comment-text',
            scope: 'form',
            hotkey: ['command+enter', 'ctrl+enter'],
            submitBtn: 'input[type = submit]'
        });
        hotkeySubmit1({
            parents: '#question',
            textArea: '.modify-comment-text',
            scope: '.add-comment',
            hotkey: ['command+enter', 'ctrl+enter'],
            submitBtn: '.action'
        });
        hotkeySubmit1({
            parents: '#answer',
            textArea: '.add-comment-text',
            scope: 'form',
            hotkey: ['command+enter', 'ctrl+enter'],
            submitBtn: 'input[type = submit]'
        });
        hotkeySubmit1({
            parents: '#answer',
            textArea: '.modify-comment-text',
            scope: '.add-comment',
            hotkey: ['command+enter', 'ctrl+enter'],
            submitBtn: '.action'
        });
        hotkeySubmit1({
            parents: '#ask',
            textArea: '#wmd-input',
            scope: '#ask',
            hotkey: ['command+enter', 'ctrl+enter'],
            submitBtn: '.ask-q-submit'
        });
        hotkeySubmit1({
            parents: '.edit-post',
            textArea: '#wmd-input',
            scope: 'body',
            hotkey: ['command+enter', 'ctrl+enter'],
            submitBtn: '.edit-q-submit'
        });
        window.console && window.console.info('   __   __________________\n _//]| | QQ 群: 206236214 |\n|____|-|__________________|  老司机~带带我~~~\n  O      OO         OO OO');
    }); 
   
	/*
    $(document).ready(function() {
        var naked_url = {
            'img': 'http://s.segmentfault.com/img/naked/?14.1.23.1'
        };
        $('.session-edge input[name=password]').nakedPassword({
            path: naked_url['img'].split("?")[0]
        });
        $('.session-edge input[name=mail]').focus();
    });*/
