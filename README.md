dir /s /b target\classes\org\nnnn\ddd\service\CacheService.class


Get-ChildItem : A positional parameter cannot be found that accepts argument 'target\classes\org\nypd\dlu\service\CacheService.class'.
At line:1 char:1
+ dir /s /b target\classes\org\nypd\dlu\service\CacheService.class
+ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    + CategoryInfo          : InvalidArgument: (:) [Get-ChildItem], ParameterBindingException
    + FullyQualifiedErrorId : PositionalParameterNotFound,Microsoft.PowerShell.Commands.GetChildItemCommand




dir /s /b target\test-classes\org\nnnn\ddd\service\CacheService.class


Get-ChildItem : A positional parameter cannot be found that accepts argument 'target\test-classes\org\nypd\dlu\service\CacheService.class'.
At line:1 char:1
+ dir /s /b target\test-classes\org\nypd\dlu\service\CacheService.class
+ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    + CategoryInfo          : InvalidArgument: (:) [Get-ChildItem], ParameterBindingException
    + FullyQualifiedErrorId : PositionalParameterNotFound,Microsoft.PowerShell.Commands.GetChildItemCommand

  
dir /s /b src\test\java\org\nnnn\ddd\service\CacheService.java

Get-ChildItem : A positional parameter cannot be found that accepts argument 'src\test\java\org\nypd\dlu\service\CacheService.class'.
At line:1 char:1
+ dir /s /b src\test\java\org\nypd\dlu\service\CacheService.class
+ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    + CategoryInfo          : InvalidArgument: (:) [Get-ChildItem], ParameterBindingException
    + FullyQualifiedErrorId : PositionalParameterNotFound,Microsoft.PowerShell.Commands.GetChildItemCommand
