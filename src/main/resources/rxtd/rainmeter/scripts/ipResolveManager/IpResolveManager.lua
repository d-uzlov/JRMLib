------------------------------------------------------------------------
---
--- This script allows skin to use set of possible sources
--- to resolve external IP.
---
--- Options: { name : type : default }
---   MaxErrorCount : int : default 4
---     Must be > 0.
---   Sources : table : default ""
---     List of possible connections.
---     Must be valid LUA table initializer.
---     Format: { {'<url>', '<regexp>'}, ... }
---
--- Return value:
--- 'ok' if options has been parsed correctly.
--- 'error' otherwise.
---
---
--- Exported functions: { name and args : return type }
---   GetUrl() : string
---     Returns URL for current connection.
---   GetRegExp() : string
---     Returns RegExp for current connection.
---
--- Bangs: { name and args }
---   Next()
---     Changes connection randomly.
---   Error()
---     Marks current connection unreliable.
---     Changes connection randomly.
---   Success()
---     Marks current connection reliable.
---
---
--- On connection or parse error "Error()" must be called.
--- On connection and parse success "Success()" must be called.
---
--- If more than 'MaxErrorCount' errors happened consequently
--- (that is, no success events for this connection between errors)
--- for a connection this connection is considered broken
--- and is not used until there are no connections left.
--- In case of success the error counter for the connection is decremented.
---
------------------------------------------------------------------------

--- index : int -> { url : string, regexp : string, errorCounter : int }
local tSources = {}
--- int: index for the 'tSources' table
local nCurrentSourceIndex = 1
--- int: option
local nMaxErrorCount = 4
--- see 'tSources' - broken connections to be reused if none left
local tDisabledSources = {}
--- boolean: indicated whether measure options are correct
local bError = false

--- Init array of URLs from measure options.
local function readSources()
    local sArray = SELF:GetOption('Sources')
    if (sArray ~= '') then
        local fTmp = loadstring('return ' .. sArray);
        if (fTmp ~= nil) then
            tSources = fTmp()
        else
            bError = true
            SKIN:Bang('!Log', 'Sources are incorrect', 'Error')
            return
        end
    else
        bError = true
        SKIN:Bang('!Log', 'Sources are missing', 'Error')
        return
    end
    local nSourcesCount = #tSources
    if (nSourcesCount == 0) then
        bError = true
        SKIN:Bang('!Log', 'Sources count is zero', 'Error')
        return
    end

    -- let's check that format of the array is as expected
    local tSourcesLocal = tSources
    for i = 1, nSourcesCount, 1 do
        if (tSourcesLocal[i] == nil
                or type(tSourcesLocal[i]) ~= 'table'
                or #tSourcesLocal[i] ~= 2
                or type(tSourcesLocal[i][1]) ~= 'string'
                or type(tSourcesLocal[i][2]) ~= 'string') then
            bError = true
            SKIN:Bang('!Log', 'Source ' .. i .. ' is incorrect', 'Error')
            return
        end
        tSourcesLocal[i][3] = 0
    end
end

function Initialize()
    bError = false
    readSources()

    if (bError) then
        return
    end

    nMaxErrorCount = SELF:GetNumberOption('MaxErrorCount', 4)
    if (nMaxErrorCount <= 0) then
        SKIN:Bang('!Log', 'MaxErrorCount=' .. SELF:GetOption('MaxErrorCount') .. ' is not valid. Falling to default 4', 'Warning')
        nMaxErrorCount = 4
    end

    return
end

--- If there is more than 1 connections left then set random connection (not equal to current) as new current.
function Next()
    if (#tSources <= 1) then
        return
    end

    local nNextIndex = math.random(1, (#tSources) - 1)
    if (nNextIndex >= nCurrentSourceIndex) then
        nNextIndex = nNextIndex + 1
    end
    nCurrentSourceIndex = nNextIndex
end

--- Mark current connection as unreliable and temporary remove it if there is too many errors for this URL.
function Error()
    local tSource = tSources[nCurrentSourceIndex]
    tSource[3] = tSource[3] + 1
    if (tSource[3] == nMaxErrorCount) then
        table.insert(tDisabledSources, tSource)
        table.remove(tSource, nCurrentSourceIndex)
    end
    if (#tSources == 0) then
        SKIN:Bang('!Log', 'No sources left, resetting error count', 'Notice')
        local tTmp = tSources
        tSources = tDisabledSources
        tDisabledSources = tTmp
    end
    Next()
end

--- Mark current connection as reliable.
function Success()
    local tSource = tSources[nCurrentSourceIndex]
    if (tSource[3] > 0) then
        tSource[3] = tSource[3] - 1
    end
end

--- Returns "string" URL for current connection.
function GetUrl()
    return tSources[nCurrentSourceIndex][1]
end

--- Returns "string" RegExp for current connection.
function GetRegExp()
    return tSources[nCurrentSourceIndex][2]
end

function Update()
    if (bError) then
        return 'error'
    else
        return 'ok'
    end
end
