(function() {
	var h = window.log || $.noop;
	var r = {
		fileError: function(v, w, u) {
			window.alert(u)
		}
	};
	var q = (window.FileReader || (window.File && window.File.prototype.getAsBinary));
	var d = !! (window.FormData);
	var s = (window.FileReader || (window.File && window.File.prototype.getAsDataURL));
	var p = !! (document.createElement("canvas").toDataURL);
	var b = p && window.atob;
	var a = !! (document.createElement("canvas").mozGetAsFile);
	var m = ((window.XMLHttpRequest && window.XMLHttpRequest.prototype.sendAsBinary) || (window.Blob && window.Uint8Array && window.ProgressEvent) || (window.ArrayBuffer && window.BlobBuilder));
	var c = !! (window.FormData);
	var k = !! (document.createElement("canvas").toDataURL);
	var t = ((q && m) || (d && c));
	var i = (s && ((b && m) || (a && c)));
	var o = s;
	var g = s && p;
	var e = function(u) {
		return u.substring(u.indexOf(",") + 1, u.length)
	};
	var j = function(x, w) {
		var z = {
			type: w.type || "",
			size: w.size || w.fileSize,
			name: w.name || w.fileName
		};
		if (x.resizeImage && !i && x.allowUploadOriginalImage) {
			h("WARN: Fall back to upload original un-resized image.");
			x.resizeImage = false
		}
		if (!x.resizeImage) {
			if (x.fileType && x.fileType.test) {
				if (!x.fileType.test(z.name.substr(z.name.lastIndexOf(".") + 1))) {
					h("ERROR: Invalid Filetype.");
					x.fileError.call(this, z, "INVALID_FILETYPE", "Invalid filetype.");
					return
				}
			}
			if (x.fileMaxSize && w.size > x.fileMaxSize) {
				h("ERROR: File exceeds size limit.");
				x.fileError.call(this, z, "FILE_EXCEEDS_SIZE_LIMIT", "File exceeds size limit.");
				return
			}
		}
		if (!x.resizeImage && d) {
			h("INFO: Bypass file reading, insert file object into FormData object directly.");
			l(x, "file", w, z)
		} else {
			if (window.FileReader) {
				h("INFO: Using FileReader to do asynchronously file reading.");
				var u = new FileReader();
				u.onerror = function(A) {
					if (A.target.error) {
						switch (A.target.error) {
							case 8:
								h("ERROR: File not found.");
								x.fileError.call(this, z, "FILE_NOT_FOUND", "File not found.");
								break;
							case 24:
								h("ERROR: File not readable.");
								x.fileError.call(this, z, "IO_ERROR", "File not readable.");
								break;
							case 18:
								h("ERROR: File cannot be access due to security constrant.");
								x.fileError.call(this, z, "SECURITY_ERROR", "File cannot be access due to security constrant.");
								break;
							case 20:
								break
						}
					}
				};
				if (!x.resizeImage) {
					if (m) {
						u.onloadend = function(B) {
							var A = B.target.result;
							l(x, "bin", A, z)
						};
						u.readAsBinaryString(w)
					} else {
						if (x.allowDataInBase64) {
							u.onloadend = function(A) {
								l(x, "base64", e(A.target.result), z)
							};
							u.readAsDataURL(w)
						} else {
							h("ERROR: No available method to extract file; allowDataInBase64 not set.");
							x.fileError.call(this, z, "NO_BIN_SUPPORT_AND_BASE64_NOT_SET", "No available method to extract file; allowDataInBase64 not set.")
						}
					}
				} else {
					u.onloadend = function(B) {
						var A = B.target.result;
						f(x, A, z, w)
					};
					u.readAsDataURL(w)
				}
			} else {
				if (window.File && window.File.prototype.getAsBinary) {
					h("WARN: FileReader does not exist, UI will be blocked when reading big file.");
					if (!x.resizeImage) {
						try {
							var v = w.getAsBinary()
						} catch (y) {
							h("ERROR: File not readable.");
							x.fileError.call(this, z, "IO_ERROR", "File not readable.");
							return
						}
						l(x, "bin", v, z)
					} else {
						try {
							var v = w.getAsDataURL()
						} catch (y) {
							h("ERROR: File not readable.");
							x.fileError.call(this, z, "IO_ERROR", "File not readable.");
							return
						}
						f(x, dataurl, z, w)
					}
				} else {
					if (x.fallback) {
						x.fallback()
					} else {
						x.fileError.call(this, z, "NOT_SUPPORT", "ERROR: No available method to extract file; this browser is not supported.")
					}
				}
			}
		}
	};
	var f = function(x, u, y, w) {
		var v = new Image();
		v.onerror = function() {
			h("ERROR: <img> failed to load, file is not a supported image format.");
			x.fileError.call(this, y, "FILE_NOT_IMAGE", "File is not a supported image format.")
		};
		v.onload = function() {
			var A = document.createElement("canvas");
			var z = function(B) {
				if (!x.imageType || x.imageType === "auto") {
					if (y.type === "image/jpeg") {
						x.imageType = "jpeg"
					} else {
						x.imageType = "png"
					}
				}
				var E = {
					type: "image/" + x.imageType,
					name: y.name.substr(0, y.name.indexOf(".")) + ".resized." + x.imageType
				};
				if (a && c) {
					var D = B.mozGetAsFile(E.name, "image/" + x.imageType);
					E.size = w.size || w.fileSize;
					l(x, "file", D, E)
				} else {
					if (b && m) /*{
						var C = window.atob(e(B.toDataURL("image/" + x.imageType)));
						E.size = C.length;
						l(x, "bin", C, E)
					} else */{
						if (x.allowDataInBase64 && p && k) {
							l(x, "base64", e(B.toDataURL("image/" + x.imageType)), E)
						} else {
							h("ERROR: No available method to extract image; allowDataInBase64 not set.");
							x.fileError.call(this, y, "NO_BIN_SUPPORT_AND_BASE64_NOT_SET", "No available method to extract file; allowDataInBase64 not set.")
						}
					}
				}
			};
			x.resizeImage(v, A, z)
		};
		v.src = u
	};
	var l = function(x, B, y, w) {
		if (c && B === "file") {
			h("INFO: Using FormData to construct form.");
			var D = new FormData();
			D.append(x.name, y);
			x.processData = false;
			x.contentType = false;
			x.__beforeSend = x.beforeSend;
			x.beforeSend = function(F, E) {
				E.data = D;
				if (E.__beforeSend) {
					return E.__beforeSend.call(this, F, E)
				}
			}
		} else {
			if (m && B === "bin") {
				h("INFO: Concat our own multipart/form-data data string.");
				if (!w.type) {
					w.type = "application/octet-stream"
				}
				if (/[^\x20-\x7E]/.test(w.name)) {
					h("INFO: Filename contains non-ASCII code, do UTF8-binary string conversion.");
					w.name_bin = unescape(encodeURIComponent(w.name))
				}
				var z = "xhrupload-" + parseInt(Math.random() * (2 << 16));
				x.contentType = "multipart/form-data; boundary=" + z;
				var D = "--" + z + '\ncontent-disposition: form-data; name="' + x.name + '"; filename="' + (w.name_bin || w.name) + '"\nContent-Type: ' + w.type + "\n\n" + y + "\n\n--" + z + "--";
				if (window.XMLHttpRequest && window.XMLHttpRequest.prototype.sendAsBinary) {
					h("INFO: Pass binary string to xhr.");
					x.data = D
				} else {
					if (window.Blob && window.Uint8Array && window.ProgressEvent) {
						h("INFO: Make XH2 blob string");
						var C = new Uint8Array(D.length);
						$.each(D, function(E, F) {
							C[E] = F.charCodeAt(0)
						});
						var u = C.buffer;
						x.processData = false;
						x.__beforeSend = x.beforeSend;
						x.beforeSend = function(F, E) {
							E.data = u;
							if (E.__beforeSend) {
								return E.__beforeSend.call(this, F, E)
							}
						}
					} else {
						h("INFO: Convert binary string into Blob.");
						var v = new ArrayBuffer(D.length);
						var C = new Uint8Array(v);
						$.each(D, function(E, F) {
							C[E] = F.charCodeAt(0)
						});
						var A = new BlobBuilder();
						A.append(v);
						var u = A.getBlob();
						x.processData = false;
						x.__beforeSend = x.beforeSend;
						x.beforeSend = function(F, E) {
							E.data = u;
							if (E.__beforeSend) {
								return E.__beforeSend.call(this, F, E)
							}
						}
					}
				}
			} else {
				if (x.allowDataInBase64 && B === "base64") {
					h("INFO: Concat our own multipart/form-data data string; send the file in base64 because binary xhr is not supported.");
					if (!w.type) {
						w.type = "application/octet-stream"
					}
					x.url += "?enc=base64";
					var z = "xhrupload-" + parseInt(Math.random() * (2 << 16));
					x.contentType = "multipart/form-data; boundary=" + z;
					x.data = "--" + z + '\ncontent-disposition: form-data; name="' + x.name + '"; filename="' + encodeURIComponent(w.name) + '.base64"\nContent-Transfer-Encoding: base64\nContent-Type: ' + w.type + "\n\n" + y + "\n\n--" + z + "--"
				} else {
					h("ERROR: Data is not given in processable form.");
					x.fileError.call(this, w, "INTERNAL_ERROR", "Data is not given in processable form.");
					return
				}
			}
		}
		n(x)
	};
	var n = function(u) {
		h("INFO: Sending file.");
		if (typeof u.data === "string" && m) {
			h("INFO: Using xhr.sendAsBinary.");
			u.___beforeSend = u.beforeSend;
			u.beforeSend = function(w, v) {
				if (window.XMLHttpRequest.prototype.sendAsBinary) {
					w.send = w.sendAsBinary
				}
				if (v.___beforeSend) {
					return v.___beforeSend.call(this, w, v)
				}
			}
		}
		$.ajax(u)
	};
	$.fn.fileUpload = function(u) {
		this.each(function(v, w) {
			if ($(w).is("input[type=file]")) {
				h("INFO: binding onchange event to a input[type=file].");
				$(w).bind("change", function() {
					if (!this.files) {
						if (u.fallback) {
							u.fallback()
						}
						return
					} else {
						if (!this.files.length) {
							h("ERROR: no file selected.");
							return
						} else {
							if (this.files.length > 1) {
								h("WARN: Multiple file upload not implemented yet, only first file will be uploaded.")
							}
						}
					}
					u.name = $(this).attr("name");
					j($.extend({}, r, u), this.files[0]);
					if (this.form.length === 1) {
						this.form.reset()
					} else {
						h("WARN: Unable to reset file selection, upload won't be triggered again if user selects the same file.")
					}
					return
				})
			}
			if ($(w).is("form")) {
				h("ERROR: <form> not implemented yet.")
			} else {
				h("INFO: binding ondrop event.");
				$(w).bind("dragover", function(x) {
					return false
				}).bind("drop", function(x) {
					if (!x.originalEvent.dataTransfer.files) {
						h("ERROR: No FileList object present; user might had dropped text.");
						return false
					}
					if (!x.originalEvent.dataTransfer.files.length) {
						h('ERROR: User had dropped a virual file (e.g. "My Computer")');
						return false
					}
					if (!x.originalEvent.dataTransfer.files.length > 1) {
						h("WARN: Multiple file upload not implemented yet, only first file will be uploaded.")
					}
					j($.extend({}, r, u), x.originalEvent.dataTransfer.files[0]);
					return false
				})
			}
		});
		return this
	};
	$.fileUploadSupported = t;
	$.imageUploadSupported = i;
	$.fileUploadAsBase64Supported = o;
	$.imageUploadAsBase64Supported = g
})();