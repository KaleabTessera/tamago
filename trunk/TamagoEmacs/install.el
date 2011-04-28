;;; Thoses command allow the installation of tamagoCDL file
;;; author : Hakim Belhaouari
;;; Copy next lines in your ~/.emacs file
;;; and precise the path to the tamagocdl-mode.el file
(autoload `tamagocdl-mode "path-to-tamagocdl-mode.el")
(add-to-list 'auto-mode-alist '("\\.cdl$" . tamagocdl-mode))
