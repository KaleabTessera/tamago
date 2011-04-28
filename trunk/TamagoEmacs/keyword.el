;; (defconst tamagocdl-font-lock-keywords-1
;;   (list
;;    (list (concat "\\<\\(#[a-z_][A-Za-z0-9_]*"
;; 	   (regexp-opt (list "@pre" "@return"))
;; 	   "\\)\\>")
;;      . 'font-lock-variable-name-face)
   
;;    (list (concat "\\<\\(" 
;; 	     (regexp-opt '(
;; 	      "id"
;; 	      "in"
;; 	      "as"
;; 	      "refine"
;; 	      "include"
;; 	      "provide"
;; 	      "require"
;; 	      "export"
;; 	      "bind"
;; 	      "use"
;; 	      "state"
;; 	      "transition"
;; 	      "default"
;; 	      "client"
;; 	      "provider"
;; 	      "forall"
;; 	      "exists"
;; 	      "by"
;; 	      "name"
;; 	      "pre"
;; 	      "post"
;; 	      "fail"
;; 	      "allow"
;; 	      "from"
;; 	      "to"
;; 	      "with"
;; 	      "when"
;; 	      "naming"
;; 	      "percolator"
;; 	      )
;; 	    )
;; 	     "\\)\\>")
;;      . 'font-lock-builtin-face)
;;    (list (concat "\\<\\(_*[A-Z][A-Za-z0-9_]*\\|"
;; 	(regexp-opt '("int"
;; 		      "boolean"
;; 		      "void"
;; 		      "real"
;; 		      "string"
;; 		      "String"
;; 		      ) 
;; 		    )
;; 	"\\)\\>"
;; 	) . 'font-lock-type-face)
;;    (list (concat "\\<\\("
;; 	     (regexp-opt '(
;; 			   "module"
;; 			   "property"
;; 			   "coreinterfaces"
;; 			   "percolators"
;; 			   "method"
;; 			   "invariant"
;; 			   "transitions"
;; 			   "states"
;; 			   "behavior"
;; 			   "binds"
;; 			   "definitions"
;; 			   "uses"
;; 			   "exportations"
;; 			   "refines"
;; 			   "includes"
;; 			   "implements"
;; 			   "using")
;; 			 )
;; 	     "\\)\\>")
;;      . 'font-lock-function-name-face)

;;    (list (regexp-opt '(
;; 	      "read"
;; 	      "write"
;; 	      "readwrite"
;; 	      "@lang")
;; 		 )
;;      . 'font-lock-warning-face)
;;    (list (regexp-opt '(
;; 	      "service"
;; 	      "component"
;; 	      "composite"
;; 	      "assembly"))
;;      . 'font-lock-keyword-face)
;;    (list (concat "\\<\\([0-9]+\\(.[0-9]+\\|[0-9]*\\)"
;; 	     (regexp-opt '("true"
;; 			   "false"
;; 			   "null"))
;; 	     "\\)\\>")
;;      . 'font-lock-constant-face)
;;    )
;;   "Minimal hightlighting entity for TamagoCDL mode")


;font-lock-variable-name-face
(concat "\\<\\(#[a-z_][A-Za-z0-9_]*\\|"
	   (regexp-opt (list "@pre" "@return"))
	   "\\)\\>")

;"\\<\\(#[a-z_][A-Za-z0-9_]*\\|@\\(?:pre\\|return\\)\\)\\>"
; bug quand on essaye de chercher les mots



;font-lock-builtin-face
(concat "\\<\\(" 
	     (regexp-opt '(
	      "id"
	      "in"
	      "as"
	      "refine"
	      "include"
	      "provide"
	      "require"
	      "export"
	      "bind"
	      "use"
	      "state"
	      "transition"
	      "default"
	      "client"
	      "provider"
	      "forall"
	      "exists"
	      "by"
	      "name"
	      "pre"
	      "post"
	      "fail"
	      "allow"
	      "from"
	      "to"
	      "with"
	      "when"
	      "naming"
	      "percolator"
	      )
	    )
	     "\\)\\>")
; "\\<\\(a\\(?:llow\\|s\\)\\|b\\(?:ind\\|y\\)\\|client\\|default\\|ex\\(?:ists\\|port\\)\\|f\\(?:ail\\|orall\\|rom\\)\\|i\\(?:nclude\\|[dn]\\)\\|nam\\(?:e\\|ing\\)\\|p\\(?:ercolator\\|ost\\|r\\(?:e\\|ovider?\\)\\)\\|re\\(?:\\(?:fin\\|quir\\)e\\)\\|state\\|t\\(?:o\\|ransition\\)\\|use\\|w\\(?:hen\\|ith\\)\\)\\>"

; font-lock-type-face
(concat "\\<\\(_*[A-Z][A-Za-z0-9_]*\\|"
	(regexp-opt '("int"
		      "bool"
		      "boolean"
		      "void"
		      "real"
		      "string"
		      "String"
		      ) 
		    )
	"\\)\\>"
	)
; "\\<\\(_*[A-Z][A-Za-z0-9_]*\\|String\\|boolean\\|int\\|real\\|string\\|void\\)\\>"


; font-lock-function-name-face
(concat "\\<\\("
	     (regexp-opt '(
			   "module"
			   "property"
			   "coreinterfaces"
			   "percolators"
			   "method"
			   "invariant"
			   "transitions"
			   "states"
			   "behavior"
			   "binds"
			   "definitions"
			   "uses"
			   "exportations"
			   "refines"
			   "includes"
			   "implements"
			   "using")
			 )
	     "\\)\\>")
; "\\<\\(b\\(?:ehavior\\|inds\\)\\|coreinterfaces\\|definitions\\|exportations\\|i\\(?:mplements\\|n\\(?:cludes\\|variant\\)\\)\\|m\\(?:ethod\\|odule\\)\\|p\\(?:ercolators\\|roperty\\)\\|refines\\|states\\|transitions\\|us\\(?:es\\|ing\\)\\)\\>"

; font-lock-warning-face
(concat "\\<\\("
		  (regexp-opt '(
							 "read"
							 "write"
							 "readwrite"
							 "@lang")
						  )
		  "\\)\\>")
; "\\<\\(@lang\\|read\\(?:write\\)?\\|write\\)\\>"

; font-lock-keyword-face
(concat "\\<\\("
		  (regexp-opt '(
							 "service"
							 "component"
							 "composite"
							 "assembly")
						  )
		  "\\)\\>")
; "\\<\\(assembly\\|compo\\(?:nent\\|site\\)\\|service\\)\\>"


; font-lock-constant-face
(concat "\\<\\([0-9]+\\(.[0-9]+\\|[0-9]*\\)\\|"
	     (regexp-opt '("true"
			   "false"
			   "null"))
	     "\\)\\>")
; "\\<\\([0-9]+\\(.[0-9]+\\|[0-9]*\\)\\|false\\|null\\|true\\)\\>"
