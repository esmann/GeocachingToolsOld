Code text default '' not null,
Name text default '' not null,
Distance real default 0.00 not null,
PlacedBy text default '' not null,
Archived integer default 0 not null,
Bearing text default '' not null,
CacheId text default '1' not null,
CacheType text default 'O' not null,
Changed text default current_date not null,
Container text default 'Unknown' not null,
County text default '' not null,
Country text default '' not null,
Degrees real default 0.00 not null,
Difficulty real default  1.0 not null,
DNF integer default  0 not null,
DNFDate text default '' not null,
Found integer default  0 not null,
FoundCount integer default  0 not null,
FoundByMeDate text default  '' not null,
FTF integer default  0 not null,
HasCorrected integer default  0 not null,
HasTravelBug integer default  0 not null,
HasUserNote integer default 0 not null,
LastFoundDate text default '' not null,
LastGPXDate text default '' not null,
LastLog text default '' not null,
LastUserDate text default current_date  not null,
Latitude text default '0.0' not null,
Lock integer default 0 not null,
LongHtm integer default 0 not null,
Longitude text default '0.0' not null,
MacroFlag integer default 0 not null,
MacroSort text default '' not null,
NumberOfLogs integer default 0 not null,
OwnerId text default '' not null,
OwnerName text default '' not null,
PlacedDate text default current_date not null,
ShortHtm integer default 0 not null,
SmartName text default '' not null,
SmartOverride integer default 0 not null,
Source text default '' not null,
State text default '' not null,
Symbol text default '' not null,
TempDisabled integer default 0 not null,
Terrain real default 1.0 not null,
UserData text default '' not null,
User2 text default '' not null,
User3 text default '' not null,
User4 text default '' not null,
UserFlag integer default 0 not null,
UserNoteDate text default  '' not null,
UserSort integer default 0 not null,
Watch integer default 0 not null,
IsOwner integer default 0 not null,
LatOriginal text default '0.0' not null,
LonOriginal text default '0.0' not null,
Created text default current_date not null,
Status text default 'A' not null,
Color text default '' not null,
ChildLoad integer default 0 not null,
LinkedTo text default '' not null,
GetPolyFlag integer default 0 not null,
Elevation real default 0.0 not null,
Resolution text default '' not null);
