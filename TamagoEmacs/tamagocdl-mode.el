
;;; tamagocdl-mode.el --- font lock support and indentation for TamagoCDL Mode

;;; Copyright (C) 2007 
;;; Author:    Hakim Belhaouari
;;; Created:   Thu Mar 22 14:44:46 CET 2007

;;; License

;; This program is free software; you can redistribute it and/or
;; modify it under the terms of the GNU General Public License
;; as published by the Free Software Foundation; either version 2
;; of the License, or (at your option) any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with this program; if not, write to the Free Software
;; Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.


;; Release notes:
;; version 0.71: correction de l'ordre des elements a ecrire
;; version 0.7:  ajout des nouveaux mots-cle (@lang{ }, using)
;; version 0.61: correct an error in a keyword (exist -> exists)
;; version 0.6 : modifying sample code
;; version 0.5 : adding new keyword with the implements
;; version 0.4 : adding the colorisation of comment and regonize the '_' underscore
;;               a character of the variable name              
;; version 0.3 : adding the menu with the generation of skeleton of entities
;; version 0.2 : adding the indentation, and increase the coloration. 
;; version 0.1 : first release coloring some part

;; CODE:

(defconst tamagocdl-version "0.71"
  "TamagoCDL mode version number.")

(require 'font-lock)   ; On force la coloration dès l'ouverture du fichier


(defvar tamagocdl-mode-hook nil)



;; ------------------- DEFINITION DE TOUCHE EN PLUS -----------------------

(defvar tamagocdl-mode-map
  (let ((tamagocdl-mode-map (make-keymap)))
    (define-key tamagocdl-mode-map "\C-j" 'newline-and-indent)
    tamagocdl-mode-map)
  "Keymap for TamagoCDL major mode")

;; --------------- Entite par DEFAUT ------------------------------------

(defvar default-service
  "module mymodule;

service MyService  {
   // implements <fullname interface>;

   // refine service <service> module <module>;
   // include service <service> module <module>;

   // property <accessmode> <fulltypes> <name>;
   //       accessmode: read readwrite write
	
   // invariant <expression> [ fail \"<msg>\" ];
	
   // method <type> <name>(<params>) {
   //   [id <identification>;]
   //   [pre <expr> [ fail \"<msg>\"];]
   //   [post <expr> [ fail \"<msg>\"];]
   // }
	
   // behavior {
   //    [default state <name>;]
   //	   states {
   //		   state <name> {
   //			   [allow <identification>;]*
   //		   }
   //       ...
   //	   }
   //	   transitions {
   //		   [transition from <name> to <name> with <identification> [when <guard>];]*
   //	   }
   // }
}
")

(defvar default-component
  "module mymodule;

component MyComponent {
   // implements <fullname interface>;

   // provide service S1 in mod1;
   // require service S2 in mod2 as lab;

   // property <accessmode> <fulltypes> <name>;
   //       accessmode: read readwrite write
	
   // percolator <shortname>;
   //     shortname: specified for each percolator
   //     example: plugin, exact
   //     put * for all available percolators (default)

   // invariant <expression> [ fail \"<msg>\" ];
	
   // method <type> <name>(<params>) {
   //   [id <identification>;]
   //   [pre <expr> [ fail \"<msg>\"];]
   //   [post <expr> [ fail \"<msg>\"];]
   // }
	
   // behavior {
   //    [default state <name>;]
   //	   states {
   //		   state <name> {
   //			   [allow <identification>;]*
   //		   }
   //       ...
   //	   }
   //	   transitions {
   //		   [transition from <name> to <name> with <identification> [when <guard>];]*
   //	   }
   // }
}
")

(defvar default-composite
  "module mymodule;

composite MyComposite {
   // implements <fullname interface>;

   // provide service <name> in <module>;
   // require service <name> in <module> as <label>;

   // property <accessmode> <fulltypes> <name>;
   //       accessmode: read readwrite write
	
   // percolator <shortname>;
   //     shortname: specified for each percolator
   //     example: plugin, exact
   //     put * for all available percolators (default)

   // invariant <expression> [ fail \"<msg>\" ];
	
   // uses <name> in <module> as <label>; // define a new component
  
   // bind service <name> module <module> client <requirer-label>
   //              provider <provider-label>;

   // export service <name> module <module> by <exporter-label>;

   // method <type> <name>(<params>) {
   //   [id <identification>;]
   //   [pre <expr> [ fail \"<msg>\"];]
   //   [post <expr> [ fail \"<msg>\"];]
   // }
	
   // behavior {
   //    [default state <name>;]
   //	   states {
   //		   state <name> {
   //			   [allow <identification>;]*
   //		   }
   //       ...
   //	   }
   //	   transitions {
   //		   [transition from <name> to <name> with <identification> [when <guard>];]*
   //	   }
   // }
}
")


(defvar default-assembly
  "module mymodule;

assembly MyAssembly {
   // uses <name> in <module> as <label>; // define a new component
  
   // bind service <name> module <module> client <requirer-label>
   //              provider <provider-label>;
}
")


;; -------------- Menu ----------------------------

;; Menu support for both XEmacs and Emacs.  If you don't have easymenu
;; with your version of Emacs, you are incompatible!
(require 'easymenu)

(defvar tamagocdl-menu nil)

(defvar tamagocdl-mode-map () "Keymap used in tamagocdl-mode buffers.")



(defun skeleton-service  ()
  (progn 
    (insert default-service)
    )
  )
(defun skeleton-composite  ()
  (progn 
    (insert default-composite)
    )
  )
(defun skeleton-component  ()
  (progn 
    (insert default-component)
    )
  )

(defun skeleton-assembly  ()
  (progn 
    (insert default-assembly)
    )
  )

(defun tamagocdl-mode-menu (modestr)
  (let ((m
	 '(["Comment Out Region"     comment-region (c-fn-region-is-active-p)]
	   ["Uncomment Region"
	    (comment-region (region-beginning) (region-end) '(4))
	    (c-fn-region-is-active-p)]
	   "---"
;	   ["Indent Expression"      c-indent-exp
;	    (memq (char-after) '(?\( ?\[ ?\{))]
	   ["Indent Line"  (tamagocdl-indent-line)]
	   "---"
	   ["Skeleton of Service"            (skeleton-service) ]
	   ["Skeleton of Component"	     (skeleton-component)]
	   ["Skeleton of Composite"          (skeleton-composite)]
	   ["Skeleton of Assembly"           (skeleton-assembly)]
	   )))
    (cons modestr m)))

(easy-menu-define tamagocdl-menu tamagocdl-mode-map "TamagoCDL Mode Commands"
		  (tamagocdl-mode-menu "TamagoCDL"))


;; ------------------ COLORATION ----------------


(defface tamagocdl-font-lock-face
  '(
	 (((type tty) (class color)) (:foreground "red"))
    (((class color) (background light)) (:foreground "Red" :bold t))
    (((class color) (background dark)) (:foreground "Pink" :bold t))
    (t (:inverse-video t :bold t))
	 )
  "Font Lock mode face for user specification."
  :group 'font-lock-highlighting-faces)


(defconst tamagocdl-font-lock-keywords-1
  (list
   '("\\(#[a-z_][A-Za-z0-9_]*\\|@\\(?:pre\\|return\\)\\)" . font-lock-variable-name-face)
   '("\\<\\(a\\(?:llow\\|s\\)\\|b\\(?:ind\\|y\\)\\|client\\|default\\|ex\\(?:ists\\|port\\)\\|f\\(?:ail\\|orall\\|rom\\)\\|i\\(?:nclude\\|[dn]\\)\\|nam\\(?:e\\|ing\\)\\|p\\(?:ercolator\\|ost\\|r\\(?:e\\|ovider?\\)\\)\\|re\\(?:\\(?:fin\\|quir\\)e\\)\\|state\\|t\\(?:o\\|ransition\\)\\|use\\|w\\(?:hen\\|ith\\)\\)\\>" . font-lock-builtin-face)
   '("\\<\\(_*[A-Z][A-Za-z0-9_]*\\|String\\|bool\\|int\\|real\\|string\\|void\\)\\>" . font-lock-type-face)
   '("\\<\\(b\\(?:ehavior\\|inds\\)\\|coreinterfaces\\|definitions\\|exportations\\|i\\(?:mplements\\|n\\(?:cludes\\|variant\\)\\)\\|m\\(?:ethod\\|odule\\)\\|p\\(?:ercolators\\|roperty\\)\\|refines\\|states\\|transitions\\|us\\(?:es\\|ing\\)\\)\\>" . font-lock-function-name-face)
   '("@lang\\|\\<\\(read\\(?:write\\)?\\|write\\)\\>" . font-lock-warning-face)
   '("\\<\\(assembly\\|compo\\(?:nent\\|site\\)\\|service\\)\\>" . font-lock-keyword-face)
   '("\\<\\([0-9]+\\(.[0-9]+\\|[0-9]*\\)\\|false\\|null\\|true\\)\\>" . font-lock-constant-face)
   )
  "Minimal hightlighting entity for TamagoCDL mode")



(defvar tamagocdl-font-lock-keywords tamagocdl-font-lock-keywords-1
  "Default highlighting expressions for TamagoCDL mode")


;; Indentation

;; largeur d'une indentation par defaut 3
(setq default-tab-width 3)

;; definition de notre fonction d'indentation
(defun tamagocdl-indent-line ()
  "Indent current line as TamagoCDL code."
  (interactive)
  (beginning-of-line)
  (if (bobp)
      (indent-line-to 0) ;; first line non-indented
    (let ((not-indented t) cur-indent)
      (if (looking-at "^.*}")
	  (progn
	    (save-excursion
	      (forward-line -1)
	      (if (looking-at "^.*{[ \t]*$") ;; dans le cas ou on a une ligne vide
		  (setq cur-indent (current-indentation))
		(setq cur-indent (- (current-indentation) default-tab-width)
		      )))
	    (if (< cur-indent 0)
		(setq cur-indent 0)))
	
	(save-excursion
	  (while not-indented ; iterate until we find an indentation hint
	    (forward-line -1)
	    (if (looking-at "^.*{")
		(progn
		  (setq cur-indent (+ (current-indentation) default-tab-width))
		  (setq not-indented nil)
		  )
	      (if (looking-at "^.*}")
		  (progn
		    (setq cur-indent (current-indentation))
		    (setq not-indented nil)
		    )
		(if (bobp)
		    (setq not-indented nil)))))))
      (if cur-indent
	  (indent-line-to cur-indent)
	(indent-line-to 0)))))


(defvar tamagocdl-mode-syntax-table
  (let ((tamagocdl-mode-syntax-table (make-syntax-table)))
	
    ; This is added so entity names with underscores can be more easily parsed
	(modify-syntax-entry ?_ "w" tamagocdl-mode-syntax-table)
	
	; Comment styles are same as C++
	(modify-syntax-entry ?/ ". 124b" tamagocdl-mode-syntax-table)
	(modify-syntax-entry ?* ". 23" tamagocdl-mode-syntax-table)
	(modify-syntax-entry ?\n "> b" tamagocdl-mode-syntax-table)
	tamagocdl-mode-syntax-table)
  "Syntax table for tamagocdl-mode")

;; --------------- Fonction definining the mode -----------------
(defun tamagocdl-mode ()
  (interactive)
  (kill-all-local-variables)
  (use-local-map tamagocdl-mode-map)
  ;; Set up font lock
  (set (make-local-variable 'font-lock-defaults) '(tamagocdl-font-lock-keywords))
  ;; Register the indentation function
  (set (make-local-variable 'indent-line-function) 'tamagocdl-indent-line)
  ;; Information
  (set-syntax-table tamagocdl-mode-syntax-table)
  (setq major-mode 'tamagocdl-mode)
  (setq mode-name "TamagoCDL")
  (run-hooks 'tamagocdl-mode-hook)
  (setq 
	comment-start "// "
	comment-end ""
	)
  (easy-menu-add (tamagocdl-mode-menu mode-name))
  )

(provide 'tamagocdl-mode)

