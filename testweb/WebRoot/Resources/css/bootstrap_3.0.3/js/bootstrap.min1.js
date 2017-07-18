/*!
* Bootstrap.js by @fat & @mdo
* Copyright 2012 Twitter, Inc.
* http://www.apache.org/licenses/LICENSE-2.0.txt
*/
!function($) {
	"use strict";$(function() {
		$.support.transition = function() {
			var transitionEnd = function() {
				var name,
					el = document.createElement("bootstrap"),
					transEndEventNames = {
						WebkitTransition : "webkitTransitionEnd",
						MozTransition : "transitionend",
						OTransition : "oTransitionEnd otransitionend",
						transition : "transitionend"
					};
				for (name in transEndEventNames)
					if (void 0 !== el.style[name]) return transEndEventNames[name]
			}();
			return transitionEnd && {
					end : transitionEnd
			}
		}()
	})
}(window.jQuery), !function($) {
	"use strict";
	var dismiss = '[data-dismiss="alert"]',
		Alert = function(el) {
			$(el).on("click", dismiss, this.close)
		};
	Alert.prototype.close = function(e) {
		function removeElement() {
			$parent.trigger("closed").remove()
		}
		var $parent,
			$this = $(this),
			selector = $this.attr("data-target");
		selector || (selector = $this.attr("href"), selector = selector && selector.replace(/.*(?=#[^\s]*$)/, "")), $parent = $(selector), e && e.preventDefault(), $parent.length || ($parent = $this.hasClass("alert") ? $this : $this.parent()), $parent.trigger(e = $.Event("close")), e.isDefaultPrevented() || ($parent.removeClass("in"), $.support.transition && $parent.hasClass("fade") ? $parent.on($.support.transition.end, removeElement) : removeElement())
	};
	var old = $.fn.alert;
	$.fn.alert = function(option) {
		return this.each(function() {
			var $this = $(this),
				data = $this.data("alert");
			data || $this.data("alert", data = new Alert(this)), "string" == typeof option && data[option].call($this)
		})
	}, $.fn.alert.Constructor = Alert, $.fn.alert.noConflict = function() {
		return $.fn.alert = old, this
	}, $(document).on("click.alert.data-api", dismiss, Alert.prototype.close)
}(window.jQuery), !function($) {
	"use strict";
	var Button = function(element, options) {
		this.$element = $(element), this.options = $.extend({}, $.fn.button.defaults, options)
	};
	Button.prototype.setState = function(state) {
		var d = "disabled",
			$el = this.$element,
			data = $el.data(),
			val = $el.is("input") ? "val" : "html";
		state += "Text", data.resetText || $el.data("resetText", $el[val]()), $el[val](data[state] || this.options[state]), setTimeout(function() {
			"loadingText" == state ? $el.addClass(d).attr(d, d) : $el.removeClass(d).removeAttr(d)
		}, 0)
	}, Button.prototype.toggle = function() {
		var $parent = this.$element.closest('[data-toggle="buttons-radio"]');
		$parent && $parent.find(".active").removeClass("active"), this.$element.toggleClass("active")
	};
	var old = $.fn.button;
	$.fn.button = function(option) {
		return this.each(function() {
			var $this = $(this),
				data = $this.data("button"),
				options = "object" == typeof option && option;
			data || $this.data("button", data = new Button(this, options)), "toggle" == option ? data.toggle() : option && data.setState(option)
		})
	}, $.fn.button.defaults = {
		loadingText : "loading..."
	}, $.fn.button.Constructor = Button, $.fn.button.noConflict = function() {
		return $.fn.button = old, this
	}, $(document).on("click.button.data-api", "[data-toggle^=button]", function(e) {
		var $btn = $(e.target);
		$btn.hasClass("btn") || ($btn = $btn.closest(".btn")), $btn.button("toggle")
	})
}(window.jQuery), !function($) {
	"use strict";
	var Carousel = function(element, options) {
		this.$element = $(element), this.options = options, "hover" == this.options.pause && this.$element.on("mouseenter", $.proxy(this.pause, this)).on("mouseleave", $.proxy(this.cycle, this))
	};
	Carousel.prototype = {
		cycle : function(e) {
			return e || (this.paused = !1), this.options.interval && !this.paused && (this.interval = setInterval($.proxy(this.next, this), this.options.interval)), this
		},
		to : function(pos) {
			var $active = this.$element.find(".item.active"),
				children = $active.parent().children(),
				activePos = children.index($active),
				that = this;
			if (!(pos > children.length - 1 || 0 > pos)) return this.sliding ? this.$element.one("slid", function() {
					that.to(pos)
				}) : activePos == pos ? this.pause().cycle() : this.slide(pos > activePos ? "next" : "prev", $(children[pos]))
		},
		pause : function(e) {
			return e || (this.paused = !0), this.$element.find(".next, .prev").length && $.support.transition.end && (this.$element.trigger($.support.transition.end), this.cycle()), clearInterval(this.interval), this.interval = null, this
		},
		next : function() {
			return this.sliding ? void 0 : this.slide("next")
		},
		prev : function() {
			return this.sliding ? void 0 : this.slide("prev")
		},
		slide : function(type, next) {
			var e,
				$active = this.$element.find(".item.active"),
				$next = next || $active[type](),
				isCycling = this.interval,
				direction = "next" == type ? "left" : "right",
				fallback = "next" == type ? "first" : "last",
				that = this;
			if (this.sliding = !0, isCycling && this.pause(), $next = $next.length ? $next : this.$element.find(".item")[fallback](), e = $.Event("slide", {
					relatedTarget : $next[0]
				}), !$next.hasClass("active")) {
				if ($.support.transition && this.$element.hasClass("slide")) {
					if (this.$element.trigger(e), e.isDefaultPrevented()) return;
					$next.addClass(type), $next[0].offsetWidth, $active.addClass(direction), $next.addClass(direction), this.$element.one($.support.transition.end, function() {
						$next.removeClass([ type, direction ].join(" ")).addClass("active"), $active.removeClass([ "active", direction ].join(" ")), that.sliding = !1, setTimeout(function() {
							that.$element.trigger("slid")
						}, 0)
					})
				} else {
					if (this.$element.trigger(e), e.isDefaultPrevented()) return;
					$active.removeClass("active"), $next.addClass("active"), this.sliding = !1, this.$element.trigger("slid")
				}
				return isCycling && this.cycle(), this
			}
		}
	};
	var old = $.fn.carousel;
	$.fn.carousel = function(option) {
		return this.each(function() {
			var $this = $(this),
				data = $this.data("carousel"),
				options = $.extend({}, $.fn.carousel.defaults, "object" == typeof option && option),
				action = "string" == typeof option ? option : options.slide;
			data || $this.data("carousel", data = new Carousel(this, options)), "number" == typeof option ? data.to(option) : action ? data[action]() : options.interval && data.cycle()
		})
	}, $.fn.carousel.defaults = {
		interval : 5e3,
		pause : "hover"
	}, $.fn.carousel.Constructor = Carousel, $.fn.carousel.noConflict = function() {
		return $.fn.carousel = old, this
	}, $(document).on("click.carousel.data-api", "[data-slide]", function(e) {
		var href,
			$this = $(this),
			$target = $($this.attr("data-target") || (href = $this.attr("href")) && href.replace(/.*(?=#[^\s]+$)/, "")),
			options = $.extend({}, $target.data(), $this.data());
		$target.carousel(options), e.preventDefault()
	})
}(window.jQuery), !function($) {
	"use strict";
	var Collapse = function(element, options) {
		this.$element = $(element), this.options = $.extend({}, $.fn.collapse.defaults, options), this.options.parent && (this.$parent = $(this.options.parent)), this.options.toggle && this.toggle()
	};
	Collapse.prototype = {
		constructor : Collapse,
		dimension : function() {
			var hasWidth = this.$element.hasClass("width");
			return hasWidth ? "width" : "height"
		},
		show : function() {
			var dimension,
				scroll,
				actives,
				hasData;
			if (!this.transitioning) {
				if (dimension = this.dimension(), scroll = $.camelCase([ "scroll", dimension ].join("-")), actives = this.$parent && this.$parent.find("> .accordion-group > .in"), actives && actives.length) {
					if (hasData = actives.data("collapse"), hasData && hasData.transitioning) return;
					actives.collapse("hide"), hasData || actives.data("collapse", null)
				}
				this.$element[dimension](0), this.transition("addClass", $.Event("show"), "shown"), $.support.transition && this.$element[dimension](this.$element[0][scroll])
			}
		},
		hide : function() {
			var dimension;
			this.transitioning || (dimension = this.dimension(), this.reset(this.$element[dimension]()), this.transition("removeClass", $.Event("hide"), "hidden"), this.$element[dimension](0))
		},
		reset : function(size) {
			var dimension = this.dimension();
			return this.$element.removeClass("collapse")[dimension](size || "auto")[0].offsetWidth, this.$element[null !== size ? "addClass" : "removeClass"]("collapse"), this
		},
		transition : function(method, startEvent, completeEvent) {
			var that = this,
				complete = function() {
					"show" == startEvent.type && that.reset(), that.transitioning = 0, that.$element.trigger(completeEvent)
				};
			this.$element.trigger(startEvent), startEvent.isDefaultPrevented() || (this.transitioning = 1, this.$element[method]("in"), $.support.transition && this.$element.hasClass("collapse") ? this.$element.one($.support.transition.end, complete) : complete())
		},
		toggle : function() {
			this[this.$element.hasClass("in") ? "hide" : "show"]()
		}
	};
	var old = $.fn.collapse;
	$.fn.collapse = function(option) {
		return this.each(function() {
			var $this = $(this),
				data = $this.data("collapse"),
				options = "object" == typeof option && option;
			data || $this.data("collapse", data = new Collapse(this, options)), "string" == typeof option && data[option]()
		})
	}, $.fn.collapse.defaults = {
		toggle : !0
	}, $.fn.collapse.Constructor = Collapse, $.fn.collapse.noConflict = function() {
		return $.fn.collapse = old, this
	}, $(document).on("click.collapse.data-api", "[data-toggle=collapse]", function(e) {
		var href,
			$this = $(this),
			target = $this.attr("data-target") || e.preventDefault() || (href = $this.attr("href")) && href.replace(/.*(?=#[^\s]+$)/, ""),
			option = $(target).data("collapse") ? "toggle" : $this.data();
		$this[$(target).hasClass("in") ? "addClass" : "removeClass"]("collapsed"), $(target).collapse(option)
	})
}(window.jQuery), !function($) {
	"use strict";
	function clearMenus() {
		$(toggle).each(function() {
			getParent($(this)).removeClass("open")
		})
	}
	function getParent($this) {
		var $parent,
			selector = $this.attr("data-target");
		return selector || (selector = $this.attr("href"), selector = selector && /#/.test(selector) && selector.replace(/.*(?=#[^\s]*$)/, "")), $parent = $(selector), $parent.length || ($parent = $this.parent()), $parent
	}
	var toggle = "[data-toggle=dropdown]",
		Dropdown = function(element) {
			var $el = $(element).on("click.dropdown.data-api", this.toggle);
			$("html").on("click.dropdown.data-api", function() {
				$el.parent().removeClass("open")
			})
		};
	Dropdown.prototype = {
		constructor : Dropdown,
		toggle : function() {
			var $parent,
				isActive,
				$this = $(this);
			if (!$this.is(".disabled, :disabled")) return $parent = getParent($this), isActive = $parent.hasClass("open"), clearMenus(), isActive || $parent.toggleClass("open"), $this.focus(), !1
		},
		keydown : function(e) {
			var $this,
				$items,
				$parent,
				isActive,
				index;
			if (/(38|40|27)/.test(e.keyCode) && ($this = $(this), e.preventDefault(), e.stopPropagation(), !$this.is(".disabled, :disabled"))) {
				if ($parent = getParent($this), isActive = $parent.hasClass("open"), !isActive || isActive && 27 == e.keyCode) return $this.click();
				$items = $("[role=menu] li:not(.divider):visible a", $parent), $items.length && (index = $items.index($items.filter(":focus")), 38 == e.keyCode && index > 0 && index--, 40 == e.keyCode && $items.length - 1 > index && index++, ~index || (index = 0), $items.eq(index).focus())
			}
		}
	};
	var old = $.fn.dropdown;
	$.fn.dropdown = function(option) {
		return this.each(function() {
			var $this = $(this),
				data = $this.data("dropdown");
			data || $this.data("dropdown", data = new Dropdown(this)), "string" == typeof option && data[option].call($this)
		})
	}, $.fn.dropdown.Constructor = Dropdown, $.fn.dropdown.noConflict = function() {
		return $.fn.dropdown = old, this
	}, $(document).on("click.dropdown.data-api touchstart.dropdown.data-api", clearMenus).on("click.dropdown touchstart.dropdown.data-api", ".dropdown form", function(e) {
		e.stopPropagation()
	}).on("touchstart.dropdown.data-api", ".dropdown-menu", function(e) {
		e.stopPropagation()
	}).on("click.dropdown.data-api touchstart.dropdown.data-api", toggle, Dropdown.prototype.toggle).on("keydown.dropdown.data-api touchstart.dropdown.data-api", toggle + ", [role=menu]", Dropdown.prototype.keydown)
}(window.jQuery), !function($) {
	"use strict";
	var Modal = function(element, options) {
		this.options = options, this.$element = $(element).delegate('[data-dismiss="modal"]', "click.dismiss.modal", $.proxy(this.hide, this)), this.options.remote && this.$element.find(".modal-body").load(this.options.remote)
	};
	Modal.prototype = {
		constructor : Modal,
		toggle : function() {
			return this[this.isShown ? "hide" : "show"]()
		},
		show : function() {
			var that = this,
				e = $.Event("show");
			this.$element.trigger(e), this.isShown || e.isDefaultPrevented() || (this.isShown = !0, this.escape(), this.backdrop(function() {
				var transition = $.support.transition && that.$element.hasClass("fade");
				that.$element.parent().length || that.$element.appendTo(document.body), that.$element.show(), transition && that.$element[0].offsetWidth, that.$element.addClass("in").attr("aria-hidden", !1), that.enforceFocus(), transition ? that.$element.one($.support.transition.end, function() {
					that.$element.focus().trigger("shown")
				}) : that.$element.focus().trigger("shown")
			}))
		},
		hide : function(e) {
			e && e.preventDefault(), e = $.Event("hide"), this.$element.trigger(e), this.isShown && !e.isDefaultPrevented() && (this.isShown = !1, this.escape(), $(document).off("focusin.modal"), this.$element.removeClass("in").attr("aria-hidden", !0), $.support.transition && this.$element.hasClass("fade") ? this.hideWithTransition() : this.hideModal())
		},
		enforceFocus : function() {
			var that = this;
			$(document).on("focusin.modal", function(e) {
				that.$element[0] === e.target || that.$element.has(e.target).length || that.$element.focus()
			})
		},
		escape : function() {
			var that = this;
			this.isShown && this.options.keyboard ? this.$element.on("keyup.dismiss.modal", function(e) {
				27 == e.which && that.hide()
			}) : this.isShown || this.$element.off("keyup.dismiss.modal")
		},
		hideWithTransition : function() {
			var that = this,
				timeout = setTimeout(function() {
					that.$element.off($.support.transition.end), that.hideModal()
				}, 500);
			this.$element.one($.support.transition.end, function() {
				clearTimeout(timeout), that.hideModal()
			})
		},
		hideModal : function() {
			this.$element.hide().trigger("hidden"), this.backdrop()
		},
		removeBackdrop : function() {
			this.$backdrop.remove(), this.$backdrop = null
		},
		backdrop : function(callback) {
			var animate = this.$element.hasClass("fade") ? "fade" : "";
			if (this.isShown && this.options.backdrop) {
				var doAnimate = $.support.transition && animate;
				this.$backdrop = $('<div class="modal-backdrop ' + animate + '" />').appendTo(document.body), this.$backdrop.click("static" == this.options.backdrop ? $.proxy(this.$element[0].focus, this.$element[0]) : $.proxy(this.hide, this)), doAnimate && this.$backdrop[0].offsetWidth, this.$backdrop.addClass("in"), doAnimate ? this.$backdrop.one($.support.transition.end, callback) : callback()
			} else
				!this.isShown && this.$backdrop ? (this.$backdrop.removeClass("in"), $.support.transition && this.$element.hasClass("fade") ? this.$backdrop.one($.support.transition.end, $.proxy(this.removeBackdrop, this)) : this.removeBackdrop()) : callback && callback()
		}
	};
	var old = $.fn.modal;
	$.fn.modal = function(option) {
		return this.each(function() {
			var $this = $(this),
				data = $this.data("modal"),
				options = $.extend({}, $.fn.modal.defaults, $this.data(), "object" == typeof option && option);
			data || $this.data("modal", data = new Modal(this, options)), "string" == typeof option ? data[option]() : options.show && data.show()
		})
	}, $.fn.modal.defaults = {
		backdrop : !0,
		keyboard : !0,
		show : !0
	}, $.fn.modal.Constructor = Modal, $.fn.modal.noConflict = function() {
		return $.fn.modal = old, this
	}, $(document).on("click.modal.data-api", '[data-toggle="modal"]', function(e) {
		var $this = $(this),
			href = $this.attr("href"),
			$target = $($this.attr("data-target") || href && href.replace(/.*(?=#[^\s]+$)/, "")),
			option = $target.data("modal") ? "toggle" : $.extend({
				remote : !/#/.test(href) && href
			}, $target.data(), $this.data());
		e.preventDefault(), $target.modal(option).one("hide", function() {
			$this.focus()
		})
	})
}(window.jQuery), !function($) {
	"use strict";
	var Tooltip = function(element, options) {
		this.init("tooltip", element, options)
	};
	Tooltip.prototype = {
		constructor : Tooltip,
		init : function(type, element, options) {
			var eventIn,
				eventOut;
			this.type = type, this.$element = $(element), this.options = this.getOptions(options), this.enabled = !0, "click" == this.options.trigger ? this.$element.on("click." + this.type, this.options.selector, $.proxy(this.toggle, this)) : "manual" != this.options.trigger && (eventIn = "hover" == this.options.trigger ? "mouseenter" : "focus", eventOut = "hover" == this.options.trigger ? "mouseleave" : "blur", this.$element.on(eventIn + "." + this.type, this.options.selector, $.proxy(this.enter, this)), this.$element.on(eventOut + "." + this.type, this.options.selector, $.proxy(this.leave, this))), this.options.selector ? this._options = $.extend({}, this.options, {
				trigger : "manual",
				selector : ""
			}) : this.fixTitle()
		},
		getOptions : function(options) {
			return options = $.extend({}, $.fn[this.type].defaults, options, this.$element.data()), options.delay && "number" == typeof options.delay && (options.delay = {
					show : options.delay,
					hide : options.delay
				}), options
		},
		enter : function(e) {
			var self = $(e.currentTarget)[this.type](this._options).data(this.type);
			return self.options.delay && self.options.delay.show ? (clearTimeout(this.timeout), self.hoverState = "in", this.timeout = setTimeout(function() {
				"in" == self.hoverState && self.show()
			}, self.options.delay.show), void 0) : self.show()
		},
		leave : function(e) {
			var self = $(e.currentTarget)[this.type](this._options).data(this.type);
			return this.timeout && clearTimeout(this.timeout), self.options.delay && self.options.delay.hide ? (self.hoverState = "out", this.timeout = setTimeout(function() {
					"out" == self.hoverState && self.hide()
				}, self.options.delay.hide), void 0) : self.hide()
		},
		show : function() {
			var $tip,
				inside,
				pos,
				actualWidth,
				actualHeight,
				placement,
				tp;
			if (this.hasContent() && this.enabled) {
				switch ($tip = this.tip(), this.setContent(), this.options.animation && $tip.addClass("fade"), placement = "function" == typeof this.options.placement ? this.options.placement.call(this, $tip[0], this.$element[0]) : this.options.placement, inside = /in/.test(placement), $tip.detach().css({
					top : 0,
					left : 0,
					display : "block"
				}).insertAfter(this.$element), pos = this.getPosition(inside), actualWidth = $tip[0].offsetWidth, actualHeight = $tip[0].offsetHeight, inside ? placement.split(" ")[1] : placement) {
				case "bottom":
					tp = {
						top : pos.top + pos.height,
						left : pos.left + pos.width / 2 - actualWidth / 2
					};
					break;case "top":
					tp = {
						top : pos.top - actualHeight,
						left : pos.left + pos.width / 2 - actualWidth / 2
					};
					break;case "left":
					tp = {
						top : pos.top + pos.height / 2 - actualHeight / 2,
						left : pos.left - actualWidth
					};
					break;case "right":
					tp = {
						top : pos.top + pos.height / 2 - actualHeight / 2,
						left : pos.left + pos.width
					}
				}
				$tip.offset(tp).addClass(placement).addClass("in")
			}
		},
		setContent : function() {
			var $tip = this.tip(),
				title = this.getTitle();
			$tip.find(".tooltip-inner")[this.options.html ? "html" : "text"](title), $tip.removeClass("fade in top bottom left right")
		},
		hide : function() {
			function removeWithAnimation() {
				var timeout = setTimeout(function() {
					$tip.off($.support.transition.end).detach()
				}, 500);
				$tip.one($.support.transition.end, function() {
					clearTimeout(timeout), $tip.detach()
				})
			}
			var $tip = this.tip();
			return $tip.removeClass("in"), $.support.transition && this.$tip.hasClass("fade") ? removeWithAnimation() : $tip.detach(), this
		},
		fixTitle : function() {
			var $e = this.$element;
			($e.attr("title") || "string" != typeof $e.attr("data-original-title")) && $e.attr("data-original-title", $e.attr("title") || "").removeAttr("title")
		},
		hasContent : function() {
			return this.getTitle()
		},
		getPosition : function(inside) {
			return $.extend({}, inside ? {
				top : 0,
				left : 0
			} : this.$element.offset(), {
				width : this.$element[0].offsetWidth,
				height : this.$element[0].offsetHeight
			})
		},
		getTitle : function() {
			var title,
				$e = this.$element,
				o = this.options;
			return title = $e.attr("data-original-title") || ("function" == typeof o.title ? o.title.call($e[0]) : o.title)
		},
		tip : function() {
			return this.$tip = this.$tip || $(this.options.template)
		},
		validate : function() {
			this.$element[0].parentNode || (this.hide(), this.$element = null, this.options = null)
		},
		enable : function() {
			this.enabled = !0
		},
		disable : function() {
			this.enabled = !1
		},
		toggleEnabled : function() {
			this.enabled = !this.enabled
		},
		toggle : function(e) {
			var self = $(e.currentTarget)[this.type](this._options).data(this.type);
			self[self.tip().hasClass("in") ? "hide" : "show"]()
		},
		destroy : function() {
			this.hide().$element.off("." + this.type).removeData(this.type)
		}
	};
	var old = $.fn.tooltip;
	$.fn.tooltip = function(option) {
		return this.each(function() {
			var $this = $(this),
				data = $this.data("tooltip"),
				options = "object" == typeof option && option;
			data || $this.data("tooltip", data = new Tooltip(this, options)), "string" == typeof option && data[option]()
		})
	}, $.fn.tooltip.Constructor = Tooltip, $.fn.tooltip.defaults = {
		animation : !0,
		placement : "top",
		selector : !1,
		template : '<div class="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>',
		trigger : "hover",
		title : "",
		delay : 0,
		html : !1
	}, $.fn.tooltip.noConflict = function() {
		return $.fn.tooltip = old, this
	}
}(window.jQuery), !function($) {
	"use strict";
	var Popover = function(element, options) {
		this.init("popover", element, options)
	};
	Popover.prototype = $.extend({}, $.fn.tooltip.Constructor.prototype, {
		constructor : Popover,
		setContent : function() {
			var $tip = this.tip(),
				title = this.getTitle(),
				content = this.getContent();
			$tip.find(".popover-title")[this.options.html ? "html" : "text"](title), $tip.find(".popover-content")[this.options.html ? "html" : "text"](content), $tip.removeClass("fade top bottom left right in")
		},
		hasContent : function() {
			return this.getTitle() || this.getContent()
		},
		getContent : function() {
			var content,
				$e = this.$element,
				o = this.options;
			return content = $e.attr("data-content") || ("function" == typeof o.content ? o.content.call($e[0]) : o.content)
		},
		tip : function() {
			return this.$tip || (this.$tip = $(this.options.template)), this.$tip
		},
		destroy : function() {
			this.hide().$element.off("." + this.type).removeData(this.type)
		}
	});
	var old = $.fn.popover;
	$.fn.popover = function(option) {
		return this.each(function() {
			var $this = $(this),
				data = $this.data("popover"),
				options = "object" == typeof option && option;
			data || $this.data("popover", data = new Popover(this, options)), "string" == typeof option && data[option]()
		})
	}, $.fn.popover.Constructor = Popover, $.fn.popover.defaults = $.extend({}, $.fn.tooltip.defaults, {
		placement : "right",
		trigger : "click",
		content : "",
		template : '<div class="popover"><div class="arrow"></div><div class="popover-inner"><h3 class="popover-title"></h3><div class="popover-content"></div></div></div>'
	}), $.fn.popover.noConflict = function() {
		return $.fn.popover = old, this
	}
}(window.jQuery), !function($) {
	"use strict";
	function ScrollSpy(element, options) {
		var href,
			process = $.proxy(this.process, this),
			$element = $(element).is("body") ? $(window) : $(element);
		this.options = $.extend({}, $.fn.scrollspy.defaults, options), this.$scrollElement = $element.on("scroll.scroll-spy.data-api", process), this.selector = (this.options.target || (href = $(element).attr("href")) && href.replace(/.*(?=#[^\s]+$)/, "") || "") + " .nav li > a", this.$body = $("body"), this.refresh(), this.process()
	}
	ScrollSpy.prototype = {
		constructor : ScrollSpy,
		refresh : function() {
			var $targets,
				self = this;
			this.offsets = $([]), this.targets = $([]), $targets = this.$body.find(this.selector).map(function() {
				var $el = $(this),
					href = $el.data("target") || $el.attr("href"),
					$href = /^#\w/.test(href) && $(href);
				return $href && $href.length && [ [ $href.position().top + self.$scrollElement.scrollTop(), href ] ] || null
			}).sort(function(a, b) {
				return a[0] - b[0]
			}).each(function() {
				self.offsets.push(this[0]), self.targets.push(this[1])
			})
		},
		process : function() {
			var i,
				scrollTop = this.$scrollElement.scrollTop() + this.options.offset,
				scrollHeight = this.$scrollElement[0].scrollHeight || this.$body[0].scrollHeight,
				maxScroll = scrollHeight - this.$scrollElement.height(),
				offsets = this.offsets,
				targets = this.targets,
				activeTarget = this.activeTarget;
			if (scrollTop >= maxScroll) return activeTarget != (i = targets.last()[0]) && this.activate(i);
			for (i = offsets.length; i--;) activeTarget != targets[i] && scrollTop >= offsets[i] && (!offsets[i + 1] || offsets[i + 1] >= scrollTop) && this.activate(targets[i])
		},
		activate : function(target) {
			var active,
				selector;
			this.activeTarget = target, $(this.selector).parent(".active").removeClass("active"), selector = this.selector + '[data-target="' + target + '"],' + this.selector + '[href="' + target + '"]', active = $(selector).parent("li").addClass("active"), active.parent(".dropdown-menu").length && (active = active.closest("li.dropdown").addClass("active")), active.trigger("activate")
		}
	};
	var old = $.fn.scrollspy;
	$.fn.scrollspy = function(option) {
		return this.each(function() {
			var $this = $(this),
				data = $this.data("scrollspy"),
				options = "object" == typeof option && option;
			data || $this.data("scrollspy", data = new ScrollSpy(this, options)), "string" == typeof option && data[option]()
		})
	}, $.fn.scrollspy.Constructor = ScrollSpy, $.fn.scrollspy.defaults = {
		offset : 10
	}, $.fn.scrollspy.noConflict = function() {
		return $.fn.scrollspy = old, this
	}, $(window).on("load", function() {
		$('[data-spy="scroll"]').each(function() {
			var $spy = $(this);
			$spy.scrollspy($spy.data())
		})
	})
}(window.jQuery), !function($) {
	"use strict";
	var Tab = function(element) {
		this.element = $(element)
	};
	Tab.prototype = {
		constructor : Tab,
		show : function() {
			var previous,
				$target,
				e,
				$this = this.element,
				$ul = $this.closest("ul:not(.dropdown-menu)"),
				selector = $this.attr("data-target");
			selector || (selector = $this.attr("href"), selector = selector && selector.replace(/.*(?=#[^\s]*$)/, "")), $this.parent("li").hasClass("active") || (previous = $ul.find(".active:last a")[0], e = $.Event("show", {
				relatedTarget : previous
			}), $this.trigger(e), e.isDefaultPrevented() || ($target = $(selector), this.activate($this.parent("li"), $ul), this.activate($target, $target.parent(), function() {
				$this.trigger({
					type : "shown",
					relatedTarget : previous
				})
			})))
		},
		activate : function(element, container, callback) {
			function next() {
				$active.removeClass("active").find("> .dropdown-menu > .active").removeClass("active"), element.addClass("active"), transition ? (element[0].offsetWidth, element.addClass("in")) : element.removeClass("fade"), element.parent(".dropdown-menu") && element.closest("li.dropdown").addClass("active"), callback && callback()
			}
			var $active = container.find("> .active"),
				transition = callback && $.support.transition && $active.hasClass("fade");
			transition ? $active.one($.support.transition.end, next) : next(), $active.removeClass("in")
		}
	};
	var old = $.fn.tab;
	$.fn.tab = function(option) {
		return this.each(function() {
			var $this = $(this),
				data = $this.data("tab");
			data || $this.data("tab", data = new Tab(this)), "string" == typeof option && data[option]()
		})
	}, $.fn.tab.Constructor = Tab, $.fn.tab.noConflict = function() {
		return $.fn.tab = old, this
	}, $(document).on("click.tab.data-api", '[data-toggle="tab"], [data-toggle="pill"]', function(e) {
		e.preventDefault(), $(this).tab("show")
	})
}(window.jQuery), !function($) {
	"use strict";
	var Typeahead = function(element, options) {
		this.$element = $(element), this.options = $.extend({}, $.fn.typeahead.defaults, options), this.matcher = this.options.matcher || this.matcher, this.sorter = this.options.sorter || this.sorter, this.highlighter = this.options.highlighter || this.highlighter, this.updater = this.options.updater || this.updater, this.source = this.options.source, this.$menu = $(this.options.menu), this.shown = !1, this.listen()
	};
	Typeahead.prototype = {
		constructor : Typeahead,
		select : function() {
			var val = this.$menu.find(".active").attr("data-value");
			return this.$element.val(this.updater(val)).change(), this.hide()
		},
		updater : function(item) {
			return item
		},
		show : function() {
			var pos = $.extend({}, this.$element.position(), {
				height : this.$element[0].offsetHeight
			});
			return this.$menu.insertAfter(this.$element).css({
					top : pos.top + pos.height,
					left : pos.left
				}).show(), this.shown = !0, this
		},
		hide : function() {
			return this.$menu.hide(), this.shown = !1, this
		},
		lookup : function() {
			var items;
			return this.query = this.$element.val(), !this.query || this.query.length < this.options.minLength ? this.shown ? this.hide() : this : (items = $.isFunction(this.source) ? this.source(this.query, $.proxy(this.process, this)) : this.source, items ? this.process(items) : this)
		},
		process : function(items) {
			var that = this;
			return items = $.grep(items, function(item) {
					return that.matcher(item)
				}), items = this.sorter(items), items.length ? this.render(items.slice(0, this.options.items)).show() : this.shown ? this.hide() : this
		},
		matcher : function(item) {
			return ~item.toLowerCase().indexOf(this.query.toLowerCase())
		},
		sorter : function(items) {
			for (var item, beginswith = [], caseSensitive = [], caseInsensitive = []; item = items.shift();) item.toLowerCase().indexOf(this.query.toLowerCase()) ? ~item.indexOf(this.query) ? caseSensitive.push(item) : caseInsensitive.push(item) : beginswith.push(item);
			return beginswith.concat(caseSensitive, caseInsensitive)
		},
		highlighter : function(item) {
			var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, "\\$&");
			return item.replace(RegExp("(" + query + ")", "ig"), function($1, match) {
				return "<strong>" + match + "</strong>"
			})
		},
		render : function(items) {
			var that = this;
			return items = $(items).map(function(i, item) {
					return i = $(that.options.item).attr("data-value", item), i.find("a").html(that.highlighter(item)), i[0]
				}), items.first().addClass("active"), this.$menu.html(items), this
		},
		next : function() {
			var active = this.$menu.find(".active").removeClass("active"),
				next = active.next();
			next.length || (next = $(this.$menu.find("li")[0])), next.addClass("active")
		},
		prev : function() {
			var active = this.$menu.find(".active").removeClass("active"),
				prev = active.prev();
			prev.length || (prev = this.$menu.find("li").last()), prev.addClass("active")
		},
		listen : function() {
			this.$element.on("blur", $.proxy(this.blur, this)).on("keypress", $.proxy(this.keypress, this)).on("keyup", $.proxy(this.keyup, this)), this.eventSupported("keydown") && this.$element.on("keydown", $.proxy(this.keydown, this)), this.$menu.on("click", $.proxy(this.click, this)).on("mouseenter", "li", $.proxy(this.mouseenter, this))
		},
		eventSupported : function(eventName) {
			var isSupported = eventName in this.$element;
			return isSupported || (this.$element.setAttribute(eventName, "return;"), isSupported = "function" == typeof this.$element[eventName]), isSupported
		},
		move : function(e) {
			if (this.shown) {
				switch (e.keyCode) {
				case 9:
				case 13:
				case 27:
					e.preventDefault();
					break;case 38:
					e.preventDefault(), this.prev();
					break;case 40:
					e.preventDefault(), this.next()
				}
				e.stopPropagation()
			}
		},
		keydown : function(e) {
			this.suppressKeyPressRepeat = ~$.inArray(e.keyCode, [ 40, 38, 9, 13, 27 ]), this.move(e)
		},
		keypress : function(e) {
			this.suppressKeyPressRepeat || this.move(e)
		},
		keyup : function(e) {
			switch (e.keyCode) {
			case 40:
			case 38:
			case 16:
			case 17:
			case 18:
				break;case 9:
			case 13:
				if (!this.shown) return;
				this.select();
				break;case 27:
				if (!this.shown) return;
				this.hide();
				break;default:
				this.lookup()
			}
			e.stopPropagation(), e.preventDefault()
		},
		blur : function() {
			var that = this;
			setTimeout(function() {
				that.hide()
			}, 150)
		},
		click : function(e) {
			e.stopPropagation(), e.preventDefault(), this.select()
		},
		mouseenter : function(e) {
			this.$menu.find(".active").removeClass("active"), $(e.currentTarget).addClass("active")
		}
	};
	var old = $.fn.typeahead;
	$.fn.typeahead = function(option) {
		return this.each(function() {
			var $this = $(this),
				data = $this.data("typeahead"),
				options = "object" == typeof option && option;
			data || $this.data("typeahead", data = new Typeahead(this, options)), "string" == typeof option && data[option]()
		})
	}, $.fn.typeahead.defaults = {
		source : [],
		items : 8,
		menu : '<ul class="typeahead dropdown-menu"></ul>',
		item : '<li><a href="#"></a></li>',
		minLength : 1
	}, $.fn.typeahead.Constructor = Typeahead, $.fn.typeahead.noConflict = function() {
		return $.fn.typeahead = old, this
	}, $(document).on("focus.typeahead.data-api", '[data-provide="typeahead"]', function(e) {
		var $this = $(this);
		$this.data("typeahead") || (e.preventDefault(), $this.typeahead($this.data()))
	})
}(window.jQuery), !function($) {
	"use strict";
	var Affix = function(element, options) {
		this.options = $.extend({}, $.fn.affix.defaults, options), this.$window = $(window).on("scroll.affix.data-api", $.proxy(this.checkPosition, this)).on("click.affix.data-api", $.proxy(function() {
			setTimeout($.proxy(this.checkPosition, this), 1)
		}, this)), this.$element = $(element), this.checkPosition()
	};
	Affix.prototype.checkPosition = function() {
		if (this.$element.is(":visible")) {
			var affix,
				scrollHeight = $(document).height(),
				scrollTop = this.$window.scrollTop(),
				position = this.$element.offset(),
				offset = this.options.offset,
				offsetBottom = offset.bottom,
				offsetTop = offset.top,
				reset = "affix affix-top affix-bottom";
			"object" != typeof offset && (offsetBottom = offsetTop = offset), "function" == typeof offsetTop && (offsetTop = offset.top()), "function" == typeof offsetBottom && (offsetBottom = offset.bottom()), affix = null != this.unpin && scrollTop + this.unpin <= position.top ? !1 : null != offsetBottom && position.top + this.$element.height() >= scrollHeight - offsetBottom ? "bottom" : null != offsetTop && offsetTop >= scrollTop ? "top" : !1, this.affixed !== affix && (this.affixed = affix, this.unpin = "bottom" == affix ? position.top - scrollTop : null, this.$element.removeClass(reset).addClass("affix" + (affix ? "-" + affix : "")))
		}
	};
	var old = $.fn.affix;
	$.fn.affix = function(option) {
		return this.each(function() {
			var $this = $(this),
				data = $this.data("affix"),
				options = "object" == typeof option && option;
			data || $this.data("affix", data = new Affix(this, options)), "string" == typeof option && data[option]()
		})
	}, $.fn.affix.Constructor = Affix, $.fn.affix.defaults = {
		offset : 0
	}, $.fn.affix.noConflict = function() {
		return $.fn.affix = old, this
	}, $(window).on("load", function() {
		$('[data-spy="affix"]').each(function() {
			var $spy = $(this),
				data = $spy.data();
			data.offset = data.offset || {}, data.offsetBottom && (data.offset.bottom = data.offsetBottom), data.offsetTop && (data.offset.top = data.offsetTop), $spy.affix(data)
		})
	})
}(window.jQuery);